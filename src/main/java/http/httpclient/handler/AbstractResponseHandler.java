package http.httpclient.handler;

import lombok.Getter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @Author: M˚Haonan
 * @Date: 2020-06-19 15:42
 * @Description: 基本响应处理器
 */
public abstract class AbstractResponseHandler implements HttpResponseHandler{

    protected final Charset charset;

    @Getter
    protected HttpResponse response;

    @Getter
    protected HttpEntity entity;

    @Getter
    protected int statusCode;

    @Getter
    protected String responseContent;


    public AbstractResponseHandler(HttpEntity entity) {
        this.charset = Charset.forName("UTF-8");
        this.entity = entity;
    }

    @Override
    public void handler(HttpResponse response) {
        try {
            this.response = response;
            this.statusCode = response.getStatusLine().getStatusCode();
        }catch (Exception e) {
             throw new RuntimeException(e);
        }
    }

    @Override
    public void handlerError(HttpResponse response) {
        this.handler(response);
    }

    @Override
    public void HandlerException(Exception exception) {
        throw new RuntimeException(exception);
    }

    @Override
    public String getResponseContent() {
        return this.responseContent;
    }

    public abstract void onHandler(HttpResponse response);
}
