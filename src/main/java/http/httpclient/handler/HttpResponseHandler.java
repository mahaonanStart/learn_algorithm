package http.httpclient.handler;

import org.apache.http.HttpResponse;

/**
 * @Author: M˚Haonan
 * @Date: 2020-06-19 15:13
 * @Description: response处理接口
 */
public interface HttpResponseHandler {

    void handler(HttpResponse response);


    void handlerError(HttpResponse response);


    void HandlerException(Exception exception);


    String getResponseContent();
}
