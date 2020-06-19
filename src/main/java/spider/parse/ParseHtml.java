package spider.parse;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import spider.test.ParseActivity;
import tools.Encodes;
import tools.ExcelUtils;
import tools.FileUtils;
import us.codecraft.xsoup.XElements;
import us.codecraft.xsoup.Xsoup;
import tools.ExcelUtils.ExcelSheet;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: M˚Haonan
 * @Date: 2020-06-18 13:09
 * @Description: html解析类
 */
@Data
@Slf4j
public class ParseHtml {


    public Elements selectList(Element element, String xpathStr) {
        XElements xElements = Xsoup.select(element, xpathStr);
        return xElements.getElements();
    }


    public String selectText(Element element, String xpathStr) {
        XElements xElements = Xsoup.select(element, xpathStr);
        return xElements.get();
    }

    public Document parseFile(File file) {
        try {
            Document document = Jsoup.parse(file, Encodes.ENCODE_UTF_8);
            return document;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String convertToJsonStr(Object obj) {
        return JSON.toJSON(obj).toString();
    }

    public void saveAsJson(Object obj, String saveDir, String fileName) {
        String json = convertToJsonStr(obj);
        FileUtils.saveFile(saveDir, fileName, json, false);
    }


    /**
     * 保存为单sheet Excel
     * @param saveDir  保存目录
     * @param fileName 文件名称
     * @param cls 数据对象类型
     * @param dataList 数据集合
     * @param <T>
     */
    public <T> void saveAsExcel(String saveDir, String fileName, Class<T> cls, List<T> dataList) {
        ExcelUtils.renderExcel(saveDir, fileName, cls, dataList);
    }

    /**
     * 保存为多sheet excel
     * @param saveDir 保存目录
     * @param fileName 文件名称
     * @param cls 数据对象类型
     * @param map k-> sheet名称  v-> 数据集合
     * @param <T> 数据类型泛型
     */
    public <T> void saveAsExcel(String saveDir, String fileName, Class<T> cls, Map<String, List<T>> map) {
        ExcelUtils.renderExcel(saveDir, fileName, cls, map);
    }


    public <T> void saveToDataBase(Class<?> cls) {

    }



    public static void main(String[] args) {
        ParseHtml html = new ParseHtml();
        html.saveToDataBase(ParseActivity.Item.class);
    }



}
