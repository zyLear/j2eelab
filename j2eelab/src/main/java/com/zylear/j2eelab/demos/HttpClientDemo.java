package com.zylear.j2eelab.demos;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Created by xiezongyu on 2017/10/17.
 */
public class HttpClientDemo {

    private void process(){
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://product.dangdang.com/1263120439.html");

            HttpHost proxy=new HttpHost("124.78.202.162", 9000);
            RequestConfig requestConfig=RequestConfig.custom().setProxy(proxy).build();
            httpGet.setConfig(requestConfig);
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
            CloseableHttpResponse response=httpClient.execute(httpGet); // 执行http get请求
            HttpEntity entity=response.getEntity(); // 获取返回实体
            System.out.println("网页内容："+ EntityUtils.toString(entity, "utf-8")); // 获取网页内容
            response.close(); // response关闭
            httpClient.close(); // httpClient关闭
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new HttpClientDemo().process();
    }

}
