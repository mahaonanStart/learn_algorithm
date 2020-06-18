package tools;

import java.io.*;
import java.lang.reflect.Field;

/**
 * @Author: M˚Haonan
 * @Date: 2020-06-18 15:23
 * @Description: 文件工具类
 */
public class FileUtils {

    /**
     * 保存文件到指定目录
     * @param path
     * @param fileName
     * @param inputStream
     */
    public static void saveFile(String path, String fileName, InputStream inputStream) {
        OutputStream os = null;
        try{
            byte[] bs = new byte[1024];
            int len;
            File temFile = new File(path);
            if (!temFile.exists()) {
                temFile.mkdirs();
            }
            os = new FileOutputStream(temFile.getPath() + File.separator + fileName);
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveFile(String path, String fileName, File file) {
        try {
            saveFile(path, fileName, new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存字符串到文件
     * @param path
     * @param fileName
     * @param str
     * @param isApppend 是否追加
     */
    public static void saveFile(String path, String fileName, String str, boolean isApppend) {
        String name = path + File.separator + fileName;
        File file = new File(name);
        FileWriter fwriter = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fwriter = new FileWriter(path + File.separator + fileName, isApppend);
            fwriter.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    public static void main(String[] args) throws Exception{
//        File file = new File("/Users/mahaonan/Downloads/神兵阁.html");
//        FileUtils.saveFile("/Users/mahaonan/mhn/test", "test.txt", file);
        FileUtils.saveFile("/Users/mahaonan/mhn/test", "test1.txt", "123我爱你123", true);
    }
}
