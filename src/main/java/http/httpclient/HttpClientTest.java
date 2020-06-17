package http.httpclient;

import http.HttpClient;
import http.HttpClientUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.MapUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: M˚Haonan
 * @Date: 2020-06-16 11:56
 * @Description: httpClient测试类
 */
public class HttpClientTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());



    @Test
    public void getTest() throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://www.cnblogs.com/kitor/p/11235762.html");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            String string = EntityUtils.toString(entity, "utf-8");
            logger.info(string);
        }
        response.close();
        httpClient.close();
    }

    public static void main(String[] args) throws Exception{
        HttpClient httpClient = HttpClient.getInstance();
        Map<String, String> headers = new HashMap<>();
        Map<String, String> params = new HashMap<>();
        headers.put("Cookie", "__mtxud=60cdff73f0fc9241.1577948051975.1577948051975.1577956359147.2; JSESSIONID=E31B5769189B100D57870C03D0A5FF0F; rememberMe=7pqIkasFqn3RGag9Ae6k5RcQLR0OfeDD9Nznog88U7e+Cfo868nI/9lBKYmepsUft4YU/Ou8aV2S5idQsvT3pVEAqS0t26FLlKkEgyoHmSRo5rzNRxIHcqFkz7yY+RJhbXqgJOxg7IVHxiDlLU/JX6XLDsmheT/dv6D22yW5asADveh83MdttGKRc1N22hAGpo7e6GCMFMQfLmiNXkbKMb6c40I134oAsiKE7eJxzM/rGxBrB+eD58LFJgmTFku3xxwVORzprMPdNynb808X1ehwylvuBUIS3BcwUoUhw+jTbiSajOpo7kkLk8F/iGq/yGZGxJletj957Lxt5/7hZqBDLdSkuQq4/OOiIlLbEwFEbbJOJv1wrLcaHtphQVeV1JT3xxNP0v/lUn4V2+R7VYdXt/47PvzCg5axpErgg2q7cMzOdc1v9eXhmD1s2Rt9JkRLqPGyDw1c56Mmu9YhMT8Nbq88iFvD/R2ZIfxjjH43CJF9uuqFIuRl0jtWvj5J");
        httpClient.post("http://eshopop.wanmei.com/wanmeiEshop/shopOrderInvoice/", params, headers);

    }
}
