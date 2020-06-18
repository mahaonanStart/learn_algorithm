package http;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.Encodes;
import tools.MapUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: M˚Haonan
 * @Date: 2020-06-16 17:48
 * @Description: httpClient 类
 */
@Slf4j
public class HttpClient {

    //httpClient的最常用实现类
    private CloseableHttpClient httpClient;

    private HttpClientContext context;

    @Setter
    private org.apache.http.impl.client.HttpClientBuilder httpClientBuilder;

    //等待数据的时间或者两个包之间的间隔时间
    @Setter
    private int socketTimeout = 10000;

    //从连接池获取连接的超时时间
    @Setter
    private int connectionRequestTimeout = 10000;

    //链接建立的超时时间
    @Setter
    private int connectTimeout = 5000;

    //最大重试连接次数
    @Setter
    private int maxAttemptTimes = 5;

    @Setter
    private InetSocketAddress globalSocketAddress = null;

    @Setter
    private Map<String, String> commonHeaders = new HashMap<>();

    @Setter
    private CredentialsProvider credentialsProvider = null;


    private static HttpClient httpClientInstance;


    public HttpClient init(HttpHost proxy) {
        RequestConfig globalConfig = RequestConfig.custom()
                .setRedirectsEnabled(false)
                .setCookieSpec(CookieSpecs.DEFAULT)
                .setSocketTimeout(socketTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setConnectTimeout(connectTimeout).build();
        this.httpClient = this.httpClientBuilder
                .setDefaultRequestConfig(globalConfig)
                .setDefaultCredentialsProvider(credentialsProvider)
                .setProxy(proxy)
                .evictExpiredConnections()
                .evictIdleConnections(10, TimeUnit.SECONDS)
                .build();
        return this;
    }

    public void get(String url) {
        get(url, null, null, null);
    }

    public void get(String url, Map<String, String> params, Map<String, String> headers, String urlEncoding) {
        HttpGet httpGet = new HttpGet(HttpClientUtils.appendParams(url, params, urlEncoding));
        HttpClientUtils.addHeaders(httpGet, commonHeaders);
        HttpClientUtils.addHeaders(httpGet, headers);

        doRequest(httpGet);
    }

    public void post(String url, Map<String, String> params) {
        post(url, params, null, null);
    }

    public void post(String url, Map<String, String> params, Map<String, String> headers) {
        post(url, params, headers, null);
    }

    public void post(String url, Map<String, String> params, Map<String, String> headers, String sendCharset) {
        HttpPost httpPost = new HttpPost(url);
        HttpClientUtils.addHeaders(httpPost, commonHeaders);
        HttpClientUtils.addHeaders(httpPost, headers);
        if (null != params) {
            List<NameValuePair> formParams = new ArrayList<>();//构建post请求参数
            params.forEach((k, v) -> {
                if (null != v) {
                    formParams.add(new BasicNameValuePair(k, v));
                }
            });
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(formParams, sendCharset)); //通过设置entity给post请求传参
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        doRequest(httpPost);
    }


    public String postFile(String url, String name, File file) {
        return postFile(url, null, null, name, file);
    }

    public String postFile(String url, Map<String, String> params, Map<String, String> headers, String name, File file) {
        Map<String, File> files = new HashMap<>();
        files.put(name, file);
        return postFile(url, params, headers, files, Encodes.ENCODE_UTF_8);
    }


    /**
     * post上传文件
     * @param url
     * @param params
     * @param headers
     * @param files
     * @param sendCharset
     * @return
     */
    public String postFile(String url, Map<String, String> params, Map<String, String> headers,  Map<String, File> files, String sendCharset) {
        HttpPost httpPost = new HttpPost(url);
        HttpClientUtils.addHeaders(httpPost, commonHeaders);
        HttpClientUtils.addHeaders(httpPost, headers);
        if (MapUtils.isNotEmpty(files)) {
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            if (params != null) {
                //设置参数的contentType
                ContentType textContentType = ContentType.create("text/plain", Charset.forName(sendCharset));
                params.forEach((k, v) -> {
                    if (v != null) {
                        entityBuilder.addTextBody(k, v, textContentType);
                    }
                });
            }
            files.forEach((k, v) -> {
                entityBuilder.addBinaryBody(k, v);
            });
            httpPost.setEntity(entityBuilder.build());
        }
        doRequest(httpPost);
        return null;
    }


    private void doRequest(HttpRequestBase httpRequest) {
        try {
            CloseableHttpResponse response = httpClient.execute(httpRequest, context);
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return;
            }
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode >= 400) {
                log.info("Status code: " + statusCode);
            }
            log.info(EntityUtils.toString(entity, "utf-8"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取httpClient的实例,如果httpClientInstance不存在，返回单例
     */
    public static HttpClient getInstance() {
        return httpClientInstance != null ? httpClientInstance : HttpClient.SingletonInstance.INSTANCE;
    }

    private static class SingletonInstance {
        private static final HttpClient INSTANCE = new HttpClientBuilder().build();
    }


    /**
     * 静态内部类，用来构建HttpClient对象,封装了apache的httpClientBuilder
     */
    public static class HttpClientBuilder {

        private int socketTimeout = 10000;

        private int connectionRequestTimeout = 10000;

        private int connectTimeout = 5000;

        private int maxAttemptTimes = 3;

        private InetSocketAddress globalSocketAddress;

        private CredentialsProvider credentialsProvider;

        private org.apache.http.impl.client.HttpClientBuilder httpClientBuilder;

        private HttpHost proxy;

        public HttpClientBuilder() {
            this.httpClientBuilder = HttpClients.custom();
        }

        public HttpClient build() {
            HttpClient httpClient = new HttpClient();
            httpClient.setHttpClientBuilder(httpClientBuilder);
            httpClient.setSocketTimeout(socketTimeout);
            httpClient.setConnectionRequestTimeout(connectionRequestTimeout);
            httpClient.setConnectTimeout(connectTimeout);
            httpClient.setMaxAttemptTimes(maxAttemptTimes);
            httpClient.setGlobalSocketAddress(globalSocketAddress);
            httpClient.setCredentialsProvider(credentialsProvider);
            return httpClient.init(proxy);
        }
    }


}
