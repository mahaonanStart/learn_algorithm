package http.httpclient;

import com.alibaba.fastjson.JSONObject;
import http.HttpClient;
import http.HttpClientUtils;
import http.httpclient.handler.*;
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

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
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
//        DefaultResponseHandler responseHandler = (DefaultResponseHandler) httpClient.get("https://segmentfault.com/q/1010000015142762/a-1020000015144063");
//        String responseContent = responseHandler.getResponseContent();
//        System.out.println(responseContent);
        headers.put("Cookie", "_wanmeiEshop_shopProduct_colSwitch_table=id%7CsellerId%7CsellerId%7CoutId%7CbarCode%7CmainImg%7Cname%7CcatName%7CshopPrice%7CmarketPrice%7CcostPrice%7CsalesNumber%7CrealSalesNumber%7CsendNumber%7CstocksNumber%7CreserveNumber%7CisOnSale%7CsingleBuyNum%7CaccLimitNum%7CtaxRate%7CpresaleRemainFundPayStartTime%7CstartSaleTime%7CendSaleTime%7Coperations; __mtxud=60cdff73f0fc9241.1577948051975.1577948051975.1577956359147.2; JSESSIONID=E31B5769189B100D57870C03D0A5FF0F; rememberMe=7pqIkasFqn3RGag9Ae6k5RcQLR0OfeDD9Nznog88U7e+Cfo868nI/9lBKYmepsUft4YU/Ou8aV2S5idQsvT3pVEAqS0t26FLlKkEgyoHmSRo5rzNRxIHcqFkz7yY+RJhbXqgJOxg7IVHxiDlLU/JX6XLDsmheT/dv6D22yW5asADveh83MdttGKRc1N22hAGpo7e6GCMFMQfLmiNXkbKMb6c40I134oAsiKE7eJxzM/rGxBrB+eD58LFJgmTFku3xxwVORzprMPdNynb808X1ehwylvuBUIS3BcwUoUhw+jTbiSajOpo7kkLk8F/iGq/yGZGxJletj957Lxt5/7hZqBDLdSkuQq4/OOiIlLbEwFEbbJOJv1wrLcaHtphQVeV1JT3xxNP0v/lUn4V2+R7VYdXt/47PvzCg5axpErgg2q7cMzOdc1v9eXhmD1s2Rt9JkRLqPGyDw1c56Mmu9YhMT8Nbq88iFvD/R2ZIfxjjH43CJF9uuqFIuRl0jtWvj5J");
        JsonResponseHandler handler = (JsonResponseHandler) httpClient.post("http://eshopop.wanmei.com/wanmeiEshop/shopOrder/list?pageSize=10&pageNumber=1&order=desc&sort=reset&id=&sellerId=&productId=&orderSn=&parentOrderId=&userId=&userName=&orderType=&orderStatus=&payStatus=&consignee=&mobile=&startAddTime=&endAddTime=&startPayTime=&endPayTime=&payHistoryId=", params, headers);
        JSONObject content = handler.getJsonContent();
        System.out.println(content);
////        http://mahaonan.cn/images/
//        httpClient.get("https://m.mycaigou.com/api/v2/search/procurement?page=1&withinXDays=&pDateSort=1");
//        FileResponseHandler handler = (FileResponseHandler) httpClient.get("https://wmeshop.oss-cn-beijing.aliyuncs.com/product/20200617/bc2eba03483449ada0e50ca62b00b672.mp4", params, headers, "utf-8");
//        handler.save("/Users/mahaonan/mhn/test");
//        DefaultResponseHandler responseHandler1 = (DefaultResponseHandler) httpClient.get("https://www.baidu.com/s?ie=UTF-8&wd=Content-Disposition");
//        System.out.println(responseHandler1.getStatusCode());
//        httpClient.postFile("http://localhost:9082/upload/img", "file", new FileInputStream(new File("/Users/mahaonan/Downloads/学习路线.jpg")));
    }
}
