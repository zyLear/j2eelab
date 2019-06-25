package com.zylear.j2eelab;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Connection.Request;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * Created by xiezongyu on 2019/5/14.
 */
public class JsoupTest {


    @Test
    public void sub() throws Exception {
        Document post = Jsoup.connect("https://weixin.sogou.com/antispider/thank.php")
                .header("Cookie", "SUV=000BEAD43AF6E68E5CD163F57323D135; ABTEST=0|1557226485|v1; IPLOC=CN3100; SUID=8EE6F63A3F18960A000000005CD163F5; PHPSESSID=bfng5dcd4kupc68609oice0i27; SUID=8EE6F63A2C12960A000000005CD163F5; JSESSIONID=aaaGv_qZ4eYxgbam4jAPw; SNUID=A7CFDF132A2CA2607EEF70E02AA30B16; successCount=1|Tue, 07 May 2019 11:20:15 GMT; seccodeErrorCount=1|Tue, 07 May 2019 11:20:41 GMT")
                .header("Host", "weixin.sogou.com")
                .header("Origin", "https://weixin.sogou.com")
//                .header("Referer", "https://weixin.sogou.com/antispider/?from=%2fweixin%3Ftype%3d1%26s_from%3dinput%26query%3dszshengxue%26ie%3dutf8%26_sug_%3dn%26_sug_type_%3d")
                .header("Referer", "https://weixin.sogou.com/antispider/?xx")

                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36")
                .header("Cache-Control", "no-cache")
//                .request()
//                .data("c=5fh8dt&r=%252Fweixin%253Ftype%253D1%2526s_from%253Dinput%2526query%253Dszshengxue%2526ie%253Dutf8%2526_sug_%253Dn%2526_sug_type_%253D&v=5")
                .data("c","4yhy73")
                .data("r","%252xx%253Ftype%253D1%2526s_from%253Dinput%2526query%253Dszshengxue%2526ie%253Dutf8%2526_sug_%253Dn%2526_sug_type_%253D")
                .data("v","5")

                .post();
        System.out.println(post);
    }

    @Test
    public void get() throws Exception{
        Document post = Jsoup.connect("https://weixin.sogou.com/weixin?type=1&s_from=input&query=xie&ie=utf8&_sug_=n&_sug_type_=")
                .header("Cookie", "SUV=000BEAD43AF6E68E5CD163F57323D135;IPLOC=CN3100; SUID=8EE6F63A3F18960A000000005CD163F5; PHPSESSID=bfng5dcd4kupc68609oice0i27;  JSESSIONID=aaaGv_qZ4eYxgbam4jAPw; SNUID=D5BDAD615B5FD28CEC5C0B605BB9F894; ")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36")
                .get();
        System.out.println(post);
    }


    @Test
    public void getP() throws Exception{
        Response response = Jsoup.connect("http://weixin.sogou.com/antispider/util/seccode.php?tc=155721535813")
                .header("Cookie", " SUV=000BEAD43AF6E68E5CD163F57323D135; ABTEST=0|1557226485|v1; IPLOC=CN3100; SUID=8EE6F63A3F18960A000000005CD163F5; PHPSESSID=bfng5dcd4kupc68609oice0i27; SUID=8EE6F63A2C12960A000000005CD163F5 ")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36")
                .header("Referer", " https://weixin.sogou.com/antispider/?from=%2fweixin%3Ftype%3d1%26s_from%3dinput%26query%3dszshengxue%26ie%3dutf8%26_sug_%3dn%26_sug_type_%3d")
                .header("Host", " weixin.sogou.com")
                .execute();
        FileUtils.writeByteArrayToFile(new File("D:\\yanzheng-jsoup.jpg"), response.bodyAsBytes());
//        System.out.println(post);


//        URL url = new URL(imgURL);
//        URLConnection uc = url.openConnection();
//        InputStream is = uc.getInputStream();
//        File file = new File("D:\\pixiv");
//        FileOutputStream out = new FileOutputStream(file);
//        int i = 0;
//
//        while ((i = is.read()) != -1) {
//            out.write(i);
//        }
//        is.close();


    }

    @Test
    public void get1() throws Exception {
        Document document = Jsoup.connect("http://mp.weixin.qq.com/profile?src=3&timestamp=1558000756&ver=1&signature=8J*gQz4aLfMyVVNAtV6H1hdsBOaGhTrXDVyY0RxsufFZbeb97s65jjIpj1aspILuwbBtht6XyfVo1wYYSC-SLA==")
                .get();
//        response.
        System.out.println(document);
    }

    @Test
    public void http() throws Exception {
        RequestBuilder requestBuilder = RequestBuilder.get().setUri("http://mp.weixin.qq.com/profile?src=3&timestamp=1557902510&ver=1&signature=9CmbrVNl5bduXqmxmzGujQ4B8VSh0axMCMQxOOU-Nz48OaXxlrLFzLyKLLR1ZWW3RWc2ddlL2leENydUg4ugKg==");

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = httpClient.execute(requestBuilder.build());
//        if (charset == null) {
//            byte[] contentBytes = IOUtils.toByteArray(httpResponse.getEntity().getContent());
//            String htmlCharset = getHtmlCharset(httpResponse, contentBytes);
//            if (htmlCharset != null) {
//                System.out.println(new String(contentBytes, htmlCharset));
//            } else {
////                logger.warn("Charset autodetect failed, use {} as charset. Please specify charset in Site.setCharset()", Charset.defaultCharset());
//                System.out.println(new String(contentBytes));
//            }
//        } else {
        System.out.println(IOUtils.toString(httpResponse.getEntity().getContent(), "utf-8"));
//        }
    }

    @Test
    public void con() throws IOException {
        for (int i = 0; i < 1000; i++) {
            RequestBuilder requestBuilder = RequestBuilder.get().setUri("http://mp.weixin.qq.com/profile?src=3&timestamp=1557933123&ver=1&signature=J6DUwPKetq*Ogq3xo2um8ypjuF2cJEUMLj1OxEfeCxW6Df6YGkQe48-1NSVUfT2Wu4FQWGfNYXBnWMCcE*gV*A==");
            CloseableHttpClient gethttpClient = HttpClients.createDefault();
            CloseableHttpResponse redirectResponse = gethttpClient.execute(requestBuilder.build());
            gethttpClient.close();
        }
    }


//    private String getHtmlCharset(HttpResponse httpResponse, byte[] contentBytes) throws IOException {
//        return CharsetUtils.detectCharset(httpResponse.getEntity().getContentType() == null ? "" : httpResponse.getEntity().getContentType().getValue(), contentBytes);
//    }

}
