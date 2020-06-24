package http.httpclient.handler;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Author: M˚Haonan
 * @Date: 2020-06-22 15:47
 * @Description: json响应处理类
 */
public class JsonResponseHandler extends DefaultResponseHandler{

    @Getter
    private JSONObject jsonContent;


    public JsonResponseHandler(HttpEntity entity) {
        super(entity);
    }

    @Override
    public void onHandler(HttpResponse response) {
        try {
            this.responseContent = EntityUtils.toString(this.entity, charset);
            this.jsonContent = JSONObject.parseObject(this.responseContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
