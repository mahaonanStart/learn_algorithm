package tools;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.Data;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: M˚Haonan
 * @Date: 2020-06-18 16:14
 * @Description: excel工具类
 */
public class ExcelUtils {

    public static String checkExcel(String fileName) {
        if(!fileName.toLowerCase().endsWith(".xlsx")) {
            fileName = fileName + ".xlsx";
        }
        return fileName;
    }


    /**
     * 保存单个sheet
     * @param path
     * @param fileName
     * @param cls
     * @param dataList
     * @param <T>
     */
    public static <T> void renderExcel(String path, String fileName, Class<T> cls, List<T> dataList) {
        String name = checkExcel(path + File.separator + fileName);
        EasyExcel.write(name, cls).sheet().doWrite(dataList);
    }

    /**
     * 保存数据类型相同的多sheet
     * @param path
     * @param fileName
     * @param cls
     * @param dataMap
     * @param <T>
     */
    public static <T> void renderExcel(String path, String fileName, Class<T> cls, Map<String, List<T>> dataMap) {
        String name = checkExcel(path + File.separator + fileName);
        ExcelWriter excelWriter = EasyExcel.write(name, cls).build();
        AtomicInteger i = new AtomicInteger();
        dataMap.forEach((k, v) -> {
            WriteSheet writeSheet = EasyExcel.writerSheet(i.getAndIncrement(), k).build();
            excelWriter.write(v, writeSheet);
        });
        excelWriter.finish();
    }


    /**
     * 保存数据类型不同的多sheet
     * @param path
     * @param fileName
     * @param excelSheets
     * @param <T>
     */
    public static <T> void renderExcel(String path, String fileName, List<ExcelSheet<T>> excelSheets) {
        String name = checkExcel(path + File.separator + fileName);
        ExcelWriter excelWriter = EasyExcel.write(name).build();
        for(ExcelSheet<T> excelSheet : excelSheets) {
            WriteSheet writeSheet = EasyExcel.writerSheet(excelSheet.getSheetNo(), excelSheet.getSheetName()).head(excelSheet.getClazz()).build();
            excelWriter.write(excelSheet.getDataList(), writeSheet);
        }
        excelWriter.finish();
    }



    @Data
    public static class ExcelSheet <T> {
        private Integer sheetNo;

        private String sheetName;

        private Class<T> clazz;

        private List<T> dataList;
    }
}
