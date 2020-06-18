package tools;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Author: M˚Haonan
 * @Date: 2020-06-17 17:15
 * @Description: 封装各种格式的编码解码工具类.
 */
public class Encodes {

    public static final String ENCODE_UTF_8 = "UTF-8";

    public static String urlEncode(String part, String charset) {
        try {
            if(null == charset || null == part) {
                return part;
            } else {
                return URLEncoder.encode(part, charset);
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("编码错误");
        }
    }
}
