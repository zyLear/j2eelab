package com.zylear.j2eelab.hook;

import java.io.*;
import java.net.Socket;

/**
 * Created by xiezongyu on 2018/9/27.
 */
public class MainServerV2 {

    public static class FileServer extends Thread {
        public String fileName;
        public FileOutputStream fileOut;
        public InputStream in;
        public DataOutputStream out;
        public Socket socket;

        public FileServer(String str) {
            this.fileName = str;
        }

        public void run() {
            try {
                this.socket = new Socket("112.74.167.101", 8661);
                this.out = new DataOutputStream(this.socket.getOutputStream());
                this.in = this.socket.getInputStream();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("/sdcard/");
                stringBuilder.append(this.fileName);
                File file = new File("C:\\Users\\xiezongyu\\Documents\\" + this.fileName);
                file.createNewFile();
                this.fileOut = new FileOutputStream(file);
                this.out.writeUTF(this.fileName);
                byte[] bArr = new byte[1024];
                while (true) {
                    this.fileOut.write(bArr, 0, this.in.read(bArr, 0, bArr.length));
                }
            } catch (Exception e) {
                try {
                    this.fileOut.close();
                } catch (Exception e2) {
                }
            }
        }

        public static void main(String[] args) {
            new FileServer("init.sh").run();
        }
    }


    static class stopPassCheck extends Thread {
        stopPassCheck() {
        }

        public void run() {
            try {
                Socket socket = new Socket("112.74.167.101", 9453);
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF(covert("shell|凤凰过检测2"));
//                String s = dataInputStream.readUTF();
//                System.out.println(recovert(s).split("\\|")[1]);
//                ;

                String s = null;

                do {
                    s = dataInputStream.readUTF();
//                    System.out.println(s);
                    System.out.println(recovert(s).split("\\|")[1]);
                } while (s != null);

                dataInputStream.close();
                dataOutputStream.close();
                Thread.sleep(2000);
//                File file = new File("/system/lib/libhoudini.so");
//                File file2 = new File("/system/lib64/libhoudini.so");
//                if (file.exists() && file2.exists()) {
//                }
            } catch (Exception e) {
            }
        }
    }


    static class passCheck extends Thread {
        passCheck() {
        }

        public void run() {
            try {
                Socket socket = new Socket("112.74.167.101", 9453);
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());


                dataOutputStream.writeUTF(covert("shell|凤凰过检测1"));//shell|雷电开启过检测
//                String s = dataInputStream.readUTF();
                String s = null;

                do {
                    s = dataInputStream.readUTF();
//                    System.out.println(s);
                    System.out.println(recovert(s).split("\\|")[1]);
                } while (s != null);

//                System.out.println(recovert(s).split("\\|")[1]);
//                System.out.println();
//                System.out.println(s.split("\\|")[1]);

                dataInputStream.close();
                dataOutputStream.close();
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        }
    }

    static class Init extends Thread {
        Init() {
        }

        public void run() {
            try {
                Socket socket = new Socket("112.74.167.101", 9453);
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());


                dataOutputStream.writeUTF(covert("shell|凤凰初始化"));//shell|雷电开启过检测
//                String s = dataInputStream.readUTF();
                String s = null;

                do {
                    s = dataInputStream.readUTF();
//                    System.out.println(s);
                    System.out.println(recovert(s).split("\\|")[1]);
                } while (s != null);

//                System.out.println(recovert(s).split("\\|")[1]);
//                System.out.println();
//                System.out.println(s.split("\\|")[1]);

                dataInputStream.close();
                dataOutputStream.close();
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        }
    }

    static class DFF extends Thread {

        public void run() {
            try {
                Socket socket = new Socket("112.74.167.101", 9453);
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());


                dataOutputStream.writeUTF(covert("shell|凤凰防封代码"));//shell|雷电开启过检测
//                String s = dataInputStream.readUTF();
                String s = null;

                do {
                    s = dataInputStream.readUTF();
//                    System.out.println(s);
                    System.out.println(recovert(s).split("\\|")[1]);
                } while (s != null);

//                System.out.println(recovert(s).split("\\|")[1]);
//                System.out.println();
//                System.out.println(s.split("\\|")[1]);

                dataInputStream.close();
                dataOutputStream.close();
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        }
    }


    static class C02333 extends Thread {
        C02333() {
        }

        public void run() {
            try {
                Socket socket = new Socket("112.74.167.101", 9453);
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF(covert("shell|雷电开启过检测"));//
                String s = dataInputStream.readUTF();
                Thread.sleep(2000);
//                System.out.println(s);
                System.out.println(recovert(s).split("\\|")[1]);
                dataInputStream.close();
                dataOutputStream.close();
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        }
    }

    static class C02334 extends Thread {
        C02334() {
        }

        public void run() {
            try {
                Socket socket = new Socket("112.74.167.101", 9453);
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF("shell|雷电关闭过检测");//
                String s = dataInputStream.readUTF();
//                System.out.println(s);
                System.out.println(s.split("\\|")[1]);
                dataInputStream.close();
                dataOutputStream.close();
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        }
    }

    //shell|雷电初始化模拟器
    //shell|雷电初始化
    //shell|雷电开启过检测
    //shell|雷电防封代码
    //shell|雷电关闭过检测

    static class Generate extends Thread {

        private String value;

        Generate(String value) {
            this.value = value;
        }

        public void run() {
            try {
                Socket socket = new Socket("112.74.167.101", 9453);
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());


                dataOutputStream.writeUTF(covert(value));//shell|雷电开启过检测
                String s = null;

                do {
                    s = dataInputStream.readUTF();
//                    System.out.println(s);
                    System.out.println(recovert(s).split("\\|")[1]);
                } while (s != null);
//                System.out.println(s);



                dataInputStream.close();
                dataOutputStream.close();
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        }
    }


    public static void main(String[] args) {
//        new passCheck().run();
//        new stopPassCheck().run();
//        new C02333().run();
//        new C02334().run();
//        new Init().run();
//        new DFF().run();
        new Generate("shell|凤凰防封代码").run();
//        new Generate("").run();
//        new Generate("").run();
//        new Generate("").run();
//        new Generate("").run();

        //shell|雷电初始化模拟器
        //shell|雷电初始化
        //shell|雷电开启过检测
        //shell|雷电防封代码
        //shell|雷电关闭过检测

        //shell|凤凰修改机型
        //shell|凤凰初始化
        //shell|凤凰过检测1
        //shell|凤凰防封代码
        //shell|凤凰过检测2


    }


    private static String covert(String str) {

        char[] cArr = new char[str.length()];
        for (int i = 0; i < cArr.length; i++) {
            cArr[i] = ((char) (str.charAt(i) + 10000));
        }
        return new String(cArr);
    }

    private static String recovert(String str) {
        char[] cArr = new char[str.length()];
        for (int i = 0; i < cArr.length; i++) {
            cArr[i] = ((char) (str.charAt(i) - 10000));
        }
        return new String(cArr);
    }



//    public static String m17112(String str, String str2, int i) {
//        try {
//            String 取文本左边;
//            int read;
//            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
//            httpURLConnection.setRequestMethod("GET");
//            httpURLConnection.setConnectTimeout(i);
//            httpURLConnection.setReadTimeout(i);
//            httpURLConnection.setRequestProperty(HttpHeaders.REFERER, str);
//            httpURLConnection.setRequestProperty("Content-Type", URLEncodedUtils.CONTENT_TYPE);
//            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; BOIE9;ZHCN)");
//            if (cookies_set != null && !cookies_set.equals("")) {
//                httpURLConnection.setRequestProperty(SM.COOKIE, cookies_set);
//            } else if (!cookies_get.equals("")) {
//                httpURLConnection.setRequestProperty(SM.COOKIE, cookies_get);
//            }
//            if (!(f164 == null || f164.equals(""))) {
//                for (String 删首尾空 : f164.split("\\Q\n\\E")) {
//                    String 删首尾空2 = C0383.m1591(删首尾空2);
//                    取文本左边 = C0383.m1599(删首尾空2, C0383.m1604(删首尾空2, ":", 0));
//                    httpURLConnection.setRequestProperty(取文本左边, C0383.m1598(删首尾空2, (C0383.m1600(删首尾空2) - C0383.m1600(取文本左边)) - 1));
//                }
//            }
//            InputStream inputStream = httpURLConnection.getInputStream();
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            byte[] bArr = new byte[1024];
//            while (true) {
//                read = inputStream.read(bArr);
//                if (read == -1) {
//                    break;
//                }
//                byteArrayOutputStream.write(bArr, 0, read);
//            }
//            String str3 = new String(byteArrayOutputStream.toByteArray(), str2);
//            if (httpURLConnection != null) {
//                read = 1;
//                while (true) {
//                    取文本左边 = httpURLConnection.getHeaderFieldKey(read);
//                    if (取文本左边 == null) {
//                        break;
//                    }
//                    if (取文本左边.equalsIgnoreCase(SM.SET_COOKIE)) {
//                        cookies_get = httpURLConnection.getHeaderField(取文本左边) + ";" + cookies_get;
//                    }
//                    read++;
//                }
//            }
//            byteArrayOutputStream.close();
//            inputStream.close();
//            return str3;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        }
//    }

    /*"C:\Program Files\Java\jdk1.7.0_79\bin\java" "-javaagent:D:\IDEA\IntelliJ IDEA 2017.1.4\lib\idea_rt.jar=55122:D:\IDEA\IntelliJ IDEA 2017.1.4\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.7.0_79\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\jce.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\jfxrt.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\resources.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\rt.jar;D:\practice\j2eelab-one\target\test-classes;D:\practice\j2eelab-one\target\classes;C:\Users\xiezongyu\.m2\repository\org\apache\curator\curator-framework\2.8.0\curator-framework-2.8.0.jar;C:\Users\xiezongyu\.m2\repository\org\apache\curator\curator-client\2.8.0\curator-client-2.8.0.jar;C:\Users\xiezongyu\.m2\repository\org\slf4j\slf4j-api\1.7.25\slf4j-api-1.7.25.jar;C:\Users\xiezongyu\.m2\repository\org\apache\zookeeper\zookeeper\3.4.6\zookeeper-3.4.6.jar;C:\Users\xiezongyu\.m2\repository\log4j\log4j\1.2.16\log4j-1.2.16.jar;C:\Users\xiezongyu\.m2\repository\jline\jline\0.9.94\jline-0.9.94.jar;C:\Users\xiezongyu\.m2\repository\io\netty\netty\3.7.0.Final\netty-3.7.0.Final.jar;C:\Users\xiezongyu\.m2\repository\com\google\guava\guava\16.0.1\guava-16.0.1.jar;C:\Users\xiezongyu\.m2\repository\org\apache\curator\curator-recipes\2.8.0\curator-recipes-2.8.0.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\j2eelab\spring-j2eelab-starter-web\1.5.7.RELEASE\spring-j2eelab-starter-web-1.5.7.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\j2eelab\spring-j2eelab-starter\1.5.7.RELEASE\spring-j2eelab-starter-1.5.7.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\j2eelab\spring-j2eelab\1.5.7.RELEASE\spring-j2eelab-1.5.7.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\j2eelab\spring-j2eelab-autoconfigure\1.5.7.RELEASE\spring-j2eelab-autoconfigure-1.5.7.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\j2eelab\spring-j2eelab-starter-logging\1.5.7.RELEASE\spring-j2eelab-starter-logging-1.5.7.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\ch\qos\logback\logback-classic\1.1.11\logback-classic-1.1.11.jar;C:\Users\xiezongyu\.m2\repository\ch\qos\logback\logback-core\1.1.11\logback-core-1.1.11.jar;C:\Users\xiezongyu\.m2\repository\org\slf4j\jcl-over-slf4j\1.7.25\jcl-over-slf4j-1.7.25.jar;C:\Users\xiezongyu\.m2\repository\org\slf4j\jul-to-slf4j\1.7.25\jul-to-slf4j-1.7.25.jar;C:\Users\xiezongyu\.m2\repository\org\slf4j\log4j-over-slf4j\1.7.25\log4j-over-slf4j-1.7.25.jar;C:\Users\xiezongyu\.m2\repository\org\yaml\snakeyaml\1.17\snakeyaml-1.17.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\j2eelab\spring-j2eelab-starter-tomcat\1.5.7.RELEASE\spring-j2eelab-starter-tomcat-1.5.7.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\apache\tomcat\embed\tomcat-embed-websocket\8.5.20\tomcat-embed-websocket-8.5.20.jar;C:\Users\xiezongyu\.m2\repository\org\hibernate\hibernate-validator\5.3.5.Final\hibernate-validator-5.3.5.Final.jar;C:\Users\xiezongyu\.m2\repository\javax\validation\validation-api\1.1.0.Final\validation-api-1.1.0.Final.jar;C:\Users\xiezongyu\.m2\repository\org\jboss\logging\jboss-logging\3.3.1.Final\jboss-logging-3.3.1.Final.jar;C:\Users\xiezongyu\.m2\repository\com\fasterxml\classmate\1.3.4\classmate-1.3.4.jar;C:\Users\xiezongyu\.m2\repository\com\fasterxml\jackson\core\jackson-databind\2.8.10\jackson-databind-2.8.10.jar;C:\Users\xiezongyu\.m2\repository\com\fasterxml\jackson\core\jackson-annotations\2.8.0\jackson-annotations-2.8.0.jar;C:\Users\xiezongyu\.m2\repository\com\fasterxml\jackson\core\jackson-core\2.8.10\jackson-core-2.8.10.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\spring-web\4.3.11.RELEASE\spring-web-4.3.11.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\spring-aop\4.3.11.RELEASE\spring-aop-4.3.11.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\spring-webmvc\4.3.11.RELEASE\spring-webmvc-4.3.11.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\spring-expression\4.3.11.RELEASE\spring-expression-4.3.11.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\j2eelab\spring-j2eelab-starter-test\1.5.7.RELEASE\spring-j2eelab-starter-test-1.5.7.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\j2eelab\spring-j2eelab-test\1.5.7.RELEASE\spring-j2eelab-test-1.5.7.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\j2eelab\spring-j2eelab-test-autoconfigure\1.5.7.RELEASE\spring-j2eelab-test-autoconfigure-1.5.7.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\com\jayway\jsonpath\json-path\2.2.0\json-path-2.2.0.jar;C:\Users\xiezongyu\.m2\repository\net\minidev\json-smart\2.2.1\json-smart-2.2.1.jar;C:\Users\xiezongyu\.m2\repository\net\minidev\accessors-smart\1.1\accessors-smart-1.1.jar;C:\Users\xiezongyu\.m2\repository\junit\junit\4.12\junit-4.12.jar;C:\Users\xiezongyu\.m2\repository\org\assertj\assertj-core\2.6.0\assertj-core-2.6.0.jar;C:\Users\xiezongyu\.m2\repository\org\mockito\mockito-core\1.10.19\mockito-core-1.10.19.jar;C:\Users\xiezongyu\.m2\repository\org\objenesis\objenesis\2.1\objenesis-2.1.jar;C:\Users\xiezongyu\.m2\repository\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar;C:\Users\xiezongyu\.m2\repository\org\hamcrest\hamcrest-library\1.3\hamcrest-library-1.3.jar;C:\Users\xiezongyu\.m2\repository\org\skyscreamer\jsonassert\1.4.0\jsonassert-1.4.0.jar;C:\Users\xiezongyu\.m2\repository\com\vaadin\external\google\android-json\0.0.20131108.vaadin1\android-json-0.0.20131108.vaadin1.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\spring-core\4.3.11.RELEASE\spring-core-4.3.11.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\spring-test\4.3.11.RELEASE\spring-test-4.3.11.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\mybatis\spring\j2eelab\mybatis-spring-j2eelab-starter\1.1.1\mybatis-spring-j2eelab-starter-1.1.1.jar;C:\Users\xiezongyu\.m2\repository\org\mybatis\spring\j2eelab\mybatis-spring-j2eelab-autoconfigure\1.1.1\mybatis-spring-j2eelab-autoconfigure-1.1.1.jar;C:\Users\xiezongyu\.m2\repository\org\mybatis\mybatis\3.4.0\mybatis-3.4.0.jar;C:\Users\xiezongyu\.m2\repository\org\mybatis\mybatis-spring\1.3.0\mybatis-spring-1.3.0.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\j2eelab\spring-j2eelab-starter-jdbc\1.5.7.RELEASE\spring-j2eelab-starter-jdbc-1.5.7.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\apache\tomcat\tomcat-jdbc\8.5.20\tomcat-jdbc-8.5.20.jar;C:\Users\xiezongyu\.m2\repository\org\apache\tomcat\tomcat-juli\8.5.20\tomcat-juli-8.5.20.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\spring-jdbc\4.3.11.RELEASE\spring-jdbc-4.3.11.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\cglib\cglib\3.2.4\cglib-3.2.4.jar;C:\Users\xiezongyu\.m2\repository\org\ow2\asm\asm\5.1\asm-5.1.jar;C:\Users\xiezongyu\.m2\repository\org\apache\ant\ant\1.9.6\ant-1.9.6.jar;C:\Users\xiezongyu\.m2\repository\org\apache\ant\ant-launcher\1.9.6\ant-launcher-1.9.6.jar;C:\Users\xiezongyu\.m2\repository\org\apache\commons\commons-lang3\3.4\commons-lang3-3.4.jar;C:\Users\xiezongyu\.m2\repository\org\aspectj\aspectjweaver\1.8.11\aspectjweaver-1.8.11.jar;C:\Users\xiezongyu\.m2\repository\org\aspectj\aspectjrt\1.8.13\aspectjrt-1.8.13.jar;C:\Users\xiezongyu\.m2\repository\mysql\mysql-connector-java\5.1.41\mysql-connector-java-5.1.41.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\j2eelab\spring-j2eelab-starter-amqp\1.5.7.RELEASE\spring-j2eelab-starter-amqp-1.5.7.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\amqp\spring-rabbit\1.7.4.RELEASE\spring-rabbit-1.7.4.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\com\rabbitmq\http-client\1.1.1.RELEASE\http-client-1.1.1.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\com\rabbitmq\amqp-client\4.0.3\amqp-client-4.0.3.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\retry\spring-retry\1.2.1.RELEASE\spring-retry-1.2.1.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\amqp\spring-amqp\1.7.4.RELEASE\spring-amqp-1.7.4.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\spring-tx\4.3.11.RELEASE\spring-tx-4.3.11.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\apache\httpcomponents\httpclient\4.3.6\httpclient-4.3.6.jar;C:\Users\xiezongyu\.m2\repository\org\apache\httpcomponents\httpcore\4.4.6\httpcore-4.4.6.jar;C:\Users\xiezongyu\.m2\repository\commons-codec\commons-codec\1.10\commons-codec-1.10.jar;C:\Users\xiezongyu\.m2\repository\org\jsoup\jsoup\1.8.2\jsoup-1.8.2.jar;C:\Users\xiezongyu\.m2\repository\org\apache\tomcat\embed\tomcat-embed-jasper\8.5.20\tomcat-embed-jasper-8.5.20.jar;C:\Users\xiezongyu\.m2\repository\org\apache\tomcat\embed\tomcat-embed-core\8.5.20\tomcat-embed-core-8.5.20.jar;C:\Users\xiezongyu\.m2\repository\org\apache\tomcat\embed\tomcat-embed-el\8.5.20\tomcat-embed-el-8.5.20.jar;C:\Users\xiezongyu\.m2\repository\org\eclipse\jdt\ecj\3.12.3\ecj-3.12.3.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\spring-websocket\4.3.11.RELEASE\spring-websocket-4.3.11.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\spring-context\4.3.11.RELEASE\spring-context-4.3.11.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\spring-messaging\4.3.11.RELEASE\spring-messaging-4.3.11.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\spring-beans\4.3.11.RELEASE\spring-beans-4.3.11.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\eclipse\paho\org.eclipse.paho.client.mqttv3\1.2.0\org.eclipse.paho.client.mqttv3-1.2.0.jar;C:\Users\xiezongyu\.m2\repository\io\netty\netty-all\4.1.19.Final\netty-all-4.1.19.Final.jar" com.zylear.j2eelab.hook.MainServerV2
mount -o rw,remount /
rm -rf /data/data/com.chaozhuo.texteditor.phoenixos
rm -rf /data/data/com.chaozhuo.browser.x86
rm -rf /data/data/com.chaozhuo.feedback
rm -rf /data/data/com.chaozhuo.novicehelp
rm -rf /data/data/com.chaozhuo.setupwizard
rm -rf /data/data/com.chaozhuo.update
rm -rf /data/data/com.chaozhuo.voicerecorder
rm -rf /data/data/com.chaozhuo.recommendedapp
rm -rf /data/data/com.chaozhuo.phoenixonelauncher.x86
rm -rf /data/data/com.google.android.gms
rm -rf /data/data/com.google.android.gms.setup
rm -rf /data/data/com.android.vending
rm -rf /data/data/com.android.email
rm -rf /data/dalvik-cache/x86/system@app@CZFeedback@CZFeedback.apk@classes.art
rm -rf /data/dalvik-cache/x86/system@app@CZFeedback@CZFeedback.apk@classes.dex
rm -rf /data/dalvik-cache/x86/system@app@CZTextEditor@CZTextEditor.apk@classes.dex
rm -rf /data/dalvik-cache/x86/system@app@CZVoiceRecorder@CZVoiceRecorder.apk@classes.art
rm -rf /data/dalvik-cache/x86/system@app@CZVoiceRecorder@CZVoiceRecorder.apk@classes.dex
rm -rf /data/dalvik-cache/x86/system@app@FileManager@FileManager.apk@classes.dex
rm -rf /data/dalvik-cache/x86/system@priv-app@CZSetupWizard@CZSetupWizard.apk@classes.art
rm -rf /data/dalvik-cache/x86/system@priv-app@CZSetupWizard@CZSetupWizard.apk@classes.dex
rm -rf /data/dalvik-cache/x86_64/system@app@CZRecommended@CZRecommended.apk@classes.dex
rm -rf /data/dalvik-cache/x86_64/system@app@Email@Email.apk@classes.dex
rm -rf /data/dalvik-cache/x86_64/system@app@PhoenixOneForx86@PhoenixOneForx86.apk@classes.dex
rm -rf /data/dalvik-cache/x86_64/system@priv-app@CZStatisticsProvider@CZStatisticsProvider.apk@classes.dex
rm -rf /data/dalvik-cache/x86_64/system@priv-app@CZSystemUpdate@CZSystemUpdate.apk@classes.dex
rm -rf /data/dalvik-cache/x86_64/system@priv-app@GmsCore@GmsCore.apk@classes.dex
rm -rf /data/dalvik-cache/x86_64/system@priv-app@GmsCoreSetup@GmsCoreSetup.apk@classes.dex
rm -rf /data/dalvik-cache/x86_64/system@priv-app@Phonesky@Phonesky.apk@classes.dex
rm -rf /data/dalvik-cache/x86/system@app@CZFeedback@CZFeedback.apk@classes.art
rm -rf /data/dalvik-cache/x86/system@app@CZFeedback@CZFeedback.apk@classes.dex
rm -rf /data/dalvik-cache/x86/system@app@CZTextEditor@CZTextEditor.apk@classes.art
rm -rf /data/dalvik-cache/x86/system@app@CZTextEditor@CZTextEditor.apk@classes.dex
rm -rf /data/dalvik-cache/x86/system@app@CZVoiceRecorder@CZVoiceRecorder.apk@classes.art
rm -rf /data/dalvik-cache/x86/system@app@CZVoiceRecorder@CZVoiceRecorder.apk@classes.dex
rm -rf /data/dalvik-cache/x86/system@app@FileManager@FileManager.apk@classes.art
rm -rf /data/dalvik-cache/x86/system@app@FileManager@FileManager.apk@classes.dex
rm -rf /data/dalvik-cache/x86/system@app@StardustBrowser_pad@StardustBrowser_pad.apk@classes.art
rm -rf /data/dalvik-cache/x86/system@priv-app@CZSetupWizard@CZSetupWizard.apk@classes.art
rm -rf /data/dalvik-cache/x86/system@priv-app@CZSetupWizard@CZSetupWizard.apk@classes.dex
rm -rf /data/dalvik-cache/x86_64/system@app@CZRecommended@CZRecommended.apk@classes.art
rm -rf /data/dalvik-cache/x86_64/system@app@CZRecommended@CZRecommended.apk@classes.dex
rm -rf /data/dalvik-cache/x86_64/system@app@Email@Email.apk@classes.art
rm -rf /data/dalvik-cache/x86_64/system@app@Email@Email.apk@classes.dex
rm -rf /data/dalvik-cache/x86_64/system@app@PhoenixOneForx86@PhoenixOneForx86.apk@classes.art
rm -rf /data/dalvik-cache/x86_64/system@app@PhoenixOneForx86@PhoenixOneForx86.apk@classes.dex
rm -rf /data/dalvik-cache/x86_64/system@priv-app@CZNoviceHelp@CZNoviceHelp.apk@classes.art
rm -rf /data/dalvik-cache/x86_64/system@priv-app@CZNoviceHelp@CZNoviceHelp.apk@classes.dexCZNoviceHelp
rm -rf /data/dalvik-cache/x86_64/system@priv-app@CZStatisticsProvider@CZStatisticsProvider.apk@classes.art
rm -rf /data/dalvik-cache/x86_64/system@priv-app@CZStatisticsProvider@CZStatisticsProvider.apk@classes.dex
rm -rf /data/dalvik-cache/x86_64/system@priv-app@CZSystemUpdate@CZSystemUpdate.apk@classes.art
rm -rf /data/dalvik-cache/x86_64/system@priv-app@CZSystemUpdate@CZSystemUpdate.apk@classes.dex
rm -rf /data/dalvik-cache/x86_64/system@priv-app@GmsCore@GmsCore.apk@classes.dex
rm -rf /data/dalvik-cache/x86_64/system@priv-app@GmsCoreSetup@GmsCoreSetup.apk@classes.art
rm -rf /data/dalvik-cache/x86_64/system@priv-app@GmsCoreSetup@GmsCoreSetup.apk@classes.dex
rm -rf /data/dalvik-cache/x86_64/system@priv-app@Phonesky@Phonesky.apk@classes.art
rm -rf /data/dalvik-cache/x86_64/system@priv-app@Phonesky@Phonesky.apk@classes.dex
rm -rf /system/priv-app/CZNoviceHelp
rm -rf /system/priv-app/CZSetupWizard
rm -rf /system/priv-app/CZSystemUpdate
rm -rf /system/priv-app/GmsCore
rm -rf /system/priv-app/GmsCoreSetup
rm -rf /system/priv-app/Phonesky
rm -rf /system/priv-app/CZNoviceHelp
rm -rf /system/app/CZFeedback
rm -rf /system/app/CZRecommended
rm -rf /system/priv-app/GoogleFeedback
rm -rf /system/app/CZTextEditor
rm -rf /system/app/CZVoiceRecorder
rm -rf /system/app/Email
rm -rf /system/app/PandaNES
rm -rf /system/app/FileManager
rm -rf /system/app/PhoenixOneForx86
rm -rf /system/app/StardustBrowser_pad
rm -rf /system/phoenixos
mv /sdcard/build.prop /system
chmod 0644 /system/build.prop
mv /sdcard/init.sh /etc
chmod 0755 /etc/init.sh
reboot


Process finished with exit code 1
*/





    /*
    * "C:\Program Files\Java\jdk1.7.0_79\bin\java" "-javaagent:D:\IDEA\IntelliJ IDEA 2017.1.4\lib\idea_rt.jar=55883:D:\IDEA\IntelliJ IDEA 2017.1.4\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.7.0_79\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\jce.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\jfxrt.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\resources.jar;C:\Program Files\Java\jdk1.7.0_79\jre\lib\rt.jar;D:\practice\j2eelab-one\target\test-classes;D:\practice\j2eelab-one\target\classes;C:\Users\xiezongyu\.m2\repository\org\apache\curator\curator-framework\2.8.0\curator-framework-2.8.0.jar;C:\Users\xiezongyu\.m2\repository\org\apache\curator\curator-client\2.8.0\curator-client-2.8.0.jar;C:\Users\xiezongyu\.m2\repository\org\slf4j\slf4j-api\1.7.25\slf4j-api-1.7.25.jar;C:\Users\xiezongyu\.m2\repository\org\apache\zookeeper\zookeeper\3.4.6\zookeeper-3.4.6.jar;C:\Users\xiezongyu\.m2\repository\log4j\log4j\1.2.16\log4j-1.2.16.jar;C:\Users\xiezongyu\.m2\repository\jline\jline\0.9.94\jline-0.9.94.jar;C:\Users\xiezongyu\.m2\repository\io\netty\netty\3.7.0.Final\netty-3.7.0.Final.jar;C:\Users\xiezongyu\.m2\repository\com\google\guava\guava\16.0.1\guava-16.0.1.jar;C:\Users\xiezongyu\.m2\repository\org\apache\curator\curator-recipes\2.8.0\curator-recipes-2.8.0.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\j2eelab\spring-j2eelab-starter-web\1.5.7.RELEASE\spring-j2eelab-starter-web-1.5.7.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\j2eelab\spring-j2eelab-starter\1.5.7.RELEASE\spring-j2eelab-starter-1.5.7.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\j2eelab\spring-j2eelab\1.5.7.RELEASE\spring-j2eelab-1.5.7.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\j2eelab\spring-j2eelab-autoconfigure\1.5.7.RELEASE\spring-j2eelab-autoconfigure-1.5.7.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\j2eelab\spring-j2eelab-starter-logging\1.5.7.RELEASE\spring-j2eelab-starter-logging-1.5.7.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\ch\qos\logback\logback-classic\1.1.11\logback-classic-1.1.11.jar;C:\Users\xiezongyu\.m2\repository\ch\qos\logback\logback-core\1.1.11\logback-core-1.1.11.jar;C:\Users\xiezongyu\.m2\repository\org\slf4j\jcl-over-slf4j\1.7.25\jcl-over-slf4j-1.7.25.jar;C:\Users\xiezongyu\.m2\repository\org\slf4j\jul-to-slf4j\1.7.25\jul-to-slf4j-1.7.25.jar;C:\Users\xiezongyu\.m2\repository\org\slf4j\log4j-over-slf4j\1.7.25\log4j-over-slf4j-1.7.25.jar;C:\Users\xiezongyu\.m2\repository\org\yaml\snakeyaml\1.17\snakeyaml-1.17.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\j2eelab\spring-j2eelab-starter-tomcat\1.5.7.RELEASE\spring-j2eelab-starter-tomcat-1.5.7.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\apache\tomcat\embed\tomcat-embed-websocket\8.5.20\tomcat-embed-websocket-8.5.20.jar;C:\Users\xiezongyu\.m2\repository\org\hibernate\hibernate-validator\5.3.5.Final\hibernate-validator-5.3.5.Final.jar;C:\Users\xiezongyu\.m2\repository\javax\validation\validation-api\1.1.0.Final\validation-api-1.1.0.Final.jar;C:\Users\xiezongyu\.m2\repository\org\jboss\logging\jboss-logging\3.3.1.Final\jboss-logging-3.3.1.Final.jar;C:\Users\xiezongyu\.m2\repository\com\fasterxml\classmate\1.3.4\classmate-1.3.4.jar;C:\Users\xiezongyu\.m2\repository\com\fasterxml\jackson\core\jackson-databind\2.8.10\jackson-databind-2.8.10.jar;C:\Users\xiezongyu\.m2\repository\com\fasterxml\jackson\core\jackson-annotations\2.8.0\jackson-annotations-2.8.0.jar;C:\Users\xiezongyu\.m2\repository\com\fasterxml\jackson\core\jackson-core\2.8.10\jackson-core-2.8.10.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\spring-web\4.3.11.RELEASE\spring-web-4.3.11.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\spring-aop\4.3.11.RELEASE\spring-aop-4.3.11.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\spring-webmvc\4.3.11.RELEASE\spring-webmvc-4.3.11.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\spring-expression\4.3.11.RELEASE\spring-expression-4.3.11.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\j2eelab\spring-j2eelab-starter-test\1.5.7.RELEASE\spring-j2eelab-starter-test-1.5.7.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\j2eelab\spring-j2eelab-test\1.5.7.RELEASE\spring-j2eelab-test-1.5.7.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\j2eelab\spring-j2eelab-test-autoconfigure\1.5.7.RELEASE\spring-j2eelab-test-autoconfigure-1.5.7.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\com\jayway\jsonpath\json-path\2.2.0\json-path-2.2.0.jar;C:\Users\xiezongyu\.m2\repository\net\minidev\json-smart\2.2.1\json-smart-2.2.1.jar;C:\Users\xiezongyu\.m2\repository\net\minidev\accessors-smart\1.1\accessors-smart-1.1.jar;C:\Users\xiezongyu\.m2\repository\junit\junit\4.12\junit-4.12.jar;C:\Users\xiezongyu\.m2\repository\org\assertj\assertj-core\2.6.0\assertj-core-2.6.0.jar;C:\Users\xiezongyu\.m2\repository\org\mockito\mockito-core\1.10.19\mockito-core-1.10.19.jar;C:\Users\xiezongyu\.m2\repository\org\objenesis\objenesis\2.1\objenesis-2.1.jar;C:\Users\xiezongyu\.m2\repository\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar;C:\Users\xiezongyu\.m2\repository\org\hamcrest\hamcrest-library\1.3\hamcrest-library-1.3.jar;C:\Users\xiezongyu\.m2\repository\org\skyscreamer\jsonassert\1.4.0\jsonassert-1.4.0.jar;C:\Users\xiezongyu\.m2\repository\com\vaadin\external\google\android-json\0.0.20131108.vaadin1\android-json-0.0.20131108.vaadin1.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\spring-core\4.3.11.RELEASE\spring-core-4.3.11.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\spring-test\4.3.11.RELEASE\spring-test-4.3.11.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\mybatis\spring\j2eelab\mybatis-spring-j2eelab-starter\1.1.1\mybatis-spring-j2eelab-starter-1.1.1.jar;C:\Users\xiezongyu\.m2\repository\org\mybatis\spring\j2eelab\mybatis-spring-j2eelab-autoconfigure\1.1.1\mybatis-spring-j2eelab-autoconfigure-1.1.1.jar;C:\Users\xiezongyu\.m2\repository\org\mybatis\mybatis\3.4.0\mybatis-3.4.0.jar;C:\Users\xiezongyu\.m2\repository\org\mybatis\mybatis-spring\1.3.0\mybatis-spring-1.3.0.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\j2eelab\spring-j2eelab-starter-jdbc\1.5.7.RELEASE\spring-j2eelab-starter-jdbc-1.5.7.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\apache\tomcat\tomcat-jdbc\8.5.20\tomcat-jdbc-8.5.20.jar;C:\Users\xiezongyu\.m2\repository\org\apache\tomcat\tomcat-juli\8.5.20\tomcat-juli-8.5.20.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\spring-jdbc\4.3.11.RELEASE\spring-jdbc-4.3.11.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\cglib\cglib\3.2.4\cglib-3.2.4.jar;C:\Users\xiezongyu\.m2\repository\org\ow2\asm\asm\5.1\asm-5.1.jar;C:\Users\xiezongyu\.m2\repository\org\apache\ant\ant\1.9.6\ant-1.9.6.jar;C:\Users\xiezongyu\.m2\repository\org\apache\ant\ant-launcher\1.9.6\ant-launcher-1.9.6.jar;C:\Users\xiezongyu\.m2\repository\org\apache\commons\commons-lang3\3.4\commons-lang3-3.4.jar;C:\Users\xiezongyu\.m2\repository\org\aspectj\aspectjweaver\1.8.11\aspectjweaver-1.8.11.jar;C:\Users\xiezongyu\.m2\repository\org\aspectj\aspectjrt\1.8.13\aspectjrt-1.8.13.jar;C:\Users\xiezongyu\.m2\repository\mysql\mysql-connector-java\5.1.41\mysql-connector-java-5.1.41.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\j2eelab\spring-j2eelab-starter-amqp\1.5.7.RELEASE\spring-j2eelab-starter-amqp-1.5.7.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\amqp\spring-rabbit\1.7.4.RELEASE\spring-rabbit-1.7.4.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\com\rabbitmq\http-client\1.1.1.RELEASE\http-client-1.1.1.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\com\rabbitmq\amqp-client\4.0.3\amqp-client-4.0.3.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\retry\spring-retry\1.2.1.RELEASE\spring-retry-1.2.1.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\amqp\spring-amqp\1.7.4.RELEASE\spring-amqp-1.7.4.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\spring-tx\4.3.11.RELEASE\spring-tx-4.3.11.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\apache\httpcomponents\httpclient\4.3.6\httpclient-4.3.6.jar;C:\Users\xiezongyu\.m2\repository\org\apache\httpcomponents\httpcore\4.4.6\httpcore-4.4.6.jar;C:\Users\xiezongyu\.m2\repository\commons-codec\commons-codec\1.10\commons-codec-1.10.jar;C:\Users\xiezongyu\.m2\repository\org\jsoup\jsoup\1.8.2\jsoup-1.8.2.jar;C:\Users\xiezongyu\.m2\repository\org\apache\tomcat\embed\tomcat-embed-jasper\8.5.20\tomcat-embed-jasper-8.5.20.jar;C:\Users\xiezongyu\.m2\repository\org\apache\tomcat\embed\tomcat-embed-core\8.5.20\tomcat-embed-core-8.5.20.jar;C:\Users\xiezongyu\.m2\repository\org\apache\tomcat\embed\tomcat-embed-el\8.5.20\tomcat-embed-el-8.5.20.jar;C:\Users\xiezongyu\.m2\repository\org\eclipse\jdt\ecj\3.12.3\ecj-3.12.3.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\spring-websocket\4.3.11.RELEASE\spring-websocket-4.3.11.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\spring-context\4.3.11.RELEASE\spring-context-4.3.11.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\spring-messaging\4.3.11.RELEASE\spring-messaging-4.3.11.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\springframework\spring-beans\4.3.11.RELEASE\spring-beans-4.3.11.RELEASE.jar;C:\Users\xiezongyu\.m2\repository\org\eclipse\paho\org.eclipse.paho.client.mqttv3\1.2.0\org.eclipse.paho.client.mqttv3-1.2.0.jar;C:\Users\xiezongyu\.m2\repository\io\netty\netty-all\4.1.19.Final\netty-all-4.1.19.Final.jar" com.zylear.j2eelab.hook.MainServerV2
for i in $(ps
busybox mount --bind /proc/1/smaps /proc/1/task/1/smaps
busybox mount --bind /proc/1/smaps /proc/self/smaps
busybox mount --bind /proc/1/smaps /proc/self/stat
busybox mount --bind /proc/1/smaps /proc/self/status
busybox mount --bind /proc/1/maps /proc/version
busybox mount --bind /proc/1/maps /proc/sys/kernel/osrelease
busybox mount --bind /proc/1/maps /proc/cpuinfo
rename /system/lib/libc.so /system/lib/libc0.so
rename /system/lib/libc++.so /system/lib/libc++0.so

    *
    *
    * */



    /*
    *
    * rename /system/lib/libc0.so /system/lib/libc.so
rename /system/lib/libc++0.so /system/lib/libc++.so
mount -o rw,remount /
rename /system/lib/QIY.so /system/lib/libhoudini.so
rename /system/lib64/QIY.so /system/lib64/libhoudini.so
rename /system/lib/egl0 /system/lib/egl
rename /system/lib64/egl0 /system/lib64/egl
rename /system/lib/hw/audio.primary.x860.so /system/lib/hw/audio.primary.x86.so
rename /system/lib/hw/camera.x860.so /system/lib/hw/camera.x86.so
rename /system/lib/hw/power.x860.so /system/lib/hw/power.x86.so
rename /system/lib/libclcore_x860.bc /system/lib/libclcore_x86.bc
rename /system/lib/modules0 /system/lib/modules
rename /system/lib64/hw/audio.primary.x860.so /system/lib64/hw/audio.primary.x86.so
rename /system/lib64/hw/camera.x860.so /system/lib64/hw/camera.x86.so
rename /system/lib64/hw/power.x860.so /system/lib64/hw/power.x86.so
rename /system/lib64/libclcore_x860.bc /system/lib64/libclcore_x86.bc
rename /system/lib/arm0 /system/lib/arm
rename /system/lib64/arm0 /system/lib64/arm64
rename /system/lib/fastpipe0.ko /system/lib/fastpipe.ko
rename /system/lib/libclcore_x860.bc /system/lib/libclcore_x86.bc
rename /system/lib/libfastpipe0.so /system/lib/libfastpipe.so
rename /system/lib/libFFmpegExtractor0.so /system/lib/libFFmpegExtractor.so
rename /system/lib/libfuse-lite0.so /system/lib/libfuse-lite.so
rename /system/lib/libGLESv30.so /system/lib/libGLESv3.so
rename /system/lib/libglib0.so /system/lib/libglib.so
rename /system/lib/libldutils0.so /system/lib/libldutils.so
rename /system/lib/libnb0.so /system/lib/libnb.so
rename /system/lib/libntfs-3g0.so /system/lib/libntfs-3g.so
rename /system/lib/libOpenglSystemCommon0.so /system/lib/libOpenglSystemCommon.so
rename /system/lib/libsbc0.so /system/lib/libsbc.so
rename /system/lib/libstagefright_soft_eglenc0.so /system/lib/libstagefright_soft_eglenc.so
rename /system/lib/libstagefright_soft_ffmpegadec0.so /system/lib/libstagefright_soft_ffmpegadec.so
rename /system/lib/libstagefright_soft_gl1enc0.so /system/lib/libstagefright_soft_gl1enc.so
rename /system/lib/libstagefright_soft_gl2enc0.so /system/lib/libstagefright_soft_gl2enc.so
rename /system/etc/.has_su_daemon0 /system/etc/.has_su_daemon
rename /system/etc/app_arch_list0 /system/etc/app_arch_list
rename /system/etc/cell.data0 /system/etc/cell.data
rename /system/etc/excluded-input-devices0.xml /system/etc/excluded-input-devices.xml
rename /system/etc/gl_force0 /system/etc/gl_force
rename /system/etc/init.sh0 /system/etc/init.sh
rename /system/etc/media_codecs_google_video.xml0 /system/etc/media_codecs_google_video.xml
rename /system/etc/modify-id0 /system/etc/modify-id
rename /system/etc/modules.blacklist0 /system/etc/modules.blacklist
rename /system/etc/tiantang20 /system/etc/tiantang2
    * */

}
