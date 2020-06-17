package http;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpRequest;
import tools.Encodes;
import tools.MapUtils;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author: M˚Haonan
 * @Date: 2020-06-17 15:36
 * @Description: http工具类
 */
public class HttpClientUtils {

    private static final String PARAMETER_START = "?";

    private static final String PARAMETER_SEPARATOR = "&";

    private static final Pattern PARAMETER_PATTERN = Pattern.compile("[?&]*(.*?)=(.*?)(?=&|$)");

    public static String appendParams(String url, String queryString) {
        return url + (url.contains(PARAMETER_START) ? PARAMETER_SEPARATOR : PARAMETER_START) + queryString;
    }

    /**
     * 按照指定的字符集拼接url和参数
     * @param url
     * @param params
     * @param charset
     * @return
     */
    public static String appendParams(String url, Map<String, String> params, String charset) {
        return appendParams(url, linkParams(params, charset));
    }

    public static String linkParams(Map<String, String> params, String charset) {
        if (null != params) {
            return MapUtils.mapToList(params, charset, v -> Encodes.urlEncode(v, charset)).stream().collect(Collectors.joining("&"));
        }
        return "";
    }

    /**
     * 设置消息头
     * @param request
     * @param headers
     */
    public static void addHeaders(HttpRequest request, Map<String, String> headers) {
        if (null != headers) {
            headers.forEach((k, v) -> request.setHeader(k, v));
        }
    }


}
