package http.httpclient.handler;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Author: M˚Haonan
 * @Date: 2020-06-19 16:05
 * @Description: 默认的响应处理器
 */
public class DefaultResponseHandler extends AbstractResponseHandler{

    public DefaultResponseHandler(HttpEntity entity) {
        super(entity);
    }

    @Override
    public void onHandler(HttpResponse response) {
        try {
            this.responseContent = EntityUtils.toString(this.entity, charset);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
