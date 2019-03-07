package com.zylear.j2eelab;

import com.zylear.j2eelab.annotation.Handle;
import com.zylear.j2eelab.hook.CodeString;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import sun.misc.Unsafe;

import javax.annotation.PostConstruct;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xiezongyu on 2017/10/9.
 */
public class PureTest {

    @Test
    public void test() {
        String string = String.format("wo%s,%s,%s,%s", "1", "1", "1", "2");
        System.out.println(string);
    }

    @Test
    public void testTwo() {


    }

    @Test
    public void testOr() {

        String string = "rm -rf /data/data/com.lalala";
        char[] chars = string.toCharArray();

        short key = 12300;
        short keyTow = 15325;


        for (int i = 0; i < chars.length; i++) {
            if (i % 2 == 0) {
                chars[i] = (char) (chars[i] ^ key);
            } else {
                chars[i] = (char) (chars[i] ^ keyTow);
            }
        }
        System.out.println(new String(chars));
        for (int i = 0; i < chars.length; i++) {
            if (i % 2 == 0) {
                chars[i] = (char) (chars[i] ^ key);
            } else {
                chars[i] = (char) (chars[i] ^ keyTow);
            }
        }
        System.out.println(new String(chars));
    }


    @Test
    public void testtt() {

        int a = 1;

        while (true) {

            try {

                if (a == 1) {
                    System.out.println("a==1");
                    continue;
                }

            } catch (Exception e) {

            } finally {

                System.out.println("final");
            }


        }


    }

    @Test
    public void writeFilesTest() {
        File file = new File("D:\\grab\\fileIndex.txt");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                System.out.println(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("test");
        System.out.println("test");

    }

    @Test
    public void testS() {
        String currentWechatGroup = "01234";
        System.out.println(currentWechatGroup.substring(0, 5));
    }

    @Test
    public void WriteFilesTest() {
        File file = new File("D:\\grab\\fileIndex.txt");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            bufferedWriter.newLine();
            bufferedWriter.write("新加的东西");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testttt() {

        try {


            try {

                if (false) {
                    throw new InterruptedException();
                }

                throw new TimeoutException();

            } catch (InterruptedException e) {

            } finally {
                try {
                    System.out.println("休息开始");
                    Thread.sleep(10000);
                    System.out.println("end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        } catch (Exception e) {
            System.out.println("捉到");
        }
    }

    @Test
    public void testParseInteger() {
        System.out.println(Integer.valueOf(""));
    }

    @Test
    public void testVoidSplit() {
        String string = "sdf ew gbdf hgfg";
        String[] strings = string.split(" ");
        for (String s : strings) {
            System.out.println(s);
        }
        System.out.println(strings.length);
        new RuntimeException();
    }

    private int getini() {
        return 10;
    }

    @Test
    public void testXpath() {
        String string = "";
    }

    @Test
    public void testStatic() {
        new BB();
    }


    @Test
    public void testSwitch() {
        int a = 1;
        switch (a) {
            case 0:
                System.out.println(0);
            case 1:
                System.out.println(1);
            case 2:
                System.out.println(2);
                break;
            default:
                System.out.println("default");
        }
    }


    @Test
    public void testJsoup() {
        String xml = "<a>co<b>ni</b></a>";
        Document document = Jsoup.parse(xml);
//        System.out.println(document.select("b").html());
//        System.out.println(document.select("b").outerHtml());
//        System.out.println(document.select("b").html("  5   "));
//        System.out.println(document.select("b").html());
//        System.out.println(document.body().html());

        Elements elements = document.select("b");
        for (Element element : elements) {
            element.text("xxxxxx");
        }
        System.out.println(document.body().html());


        LinkedList<Document> documents = new LinkedList<>();
        for (int i = 1; i < 5; i++) {
            next(documents);
        }

    }

    private void next(List<Document> list) {
        for (int i = 1; i < 1000; i++) {
            Document document1 = Jsoup.parse(i + "");
            System.out.println(i);
            list.add(document1);
        }
    }

    @Test
    public void testStackOverflow() {
        tt(0);
    }

    private void tt(int depth) {
        System.out.println(++depth);
        tt(depth);
    }

    @Test
    public void testNullJsoup() {
        Jsoup.parse(null);
    }

    @Test
    public void testSize() {
        ArrayList<String> arrayList = new ArrayList<String>(2);
        System.out.println(arrayList.size());
        for (String string : arrayList) {
            System.out.println(string);
        }
    }

    @Test
    public void testListGetNull() {
        Map<String, String> map = new HashMap<String, String>();
        System.out.println(map.get("dfsfd"));
    }

    @Test
    public void testTime() {
        Calendar instance = Calendar.getInstance();
        int hoursOfDay = instance.get(Calendar.HOUR_OF_DAY);
        if (hoursOfDay < 8 && hoursOfDay > 23) {
            System.out.println(2);
        }
        System.out.println(10);
    }


    @Test
    public void testCompareNull() {
        Integer d = null;
        if (1 < d) {
            System.out.println("datra");
        } else {
            System.out.println("ssss");
        }
    }

    @Test
    public void testSplit() {
        String string = "aaa111";
        System.out.println(Arrays.asList(string.split("aaa")));

        HashMap hashMap;

    }

    @Test
    public void testEmpty() {

        Map<String, AA> robotConfigMapCache = Collections.EMPTY_MAP;
        System.out.println(robotConfigMapCache.get("1"));
    }

    @Test
    public void testSet() {
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        System.out.println(set.toString());
    }

    @Test
    public void testList() {
        List<Student> list = new ArrayList<>();
        Student a = new Student();
        a.setName("a");
        a.setNumber(1);
        Student b = new Student();
        b.setName("b");
        b.setNumber(2);
        list.add(a);
        list.add(b);
//        System.out.println(Stringutil);
        System.out.println(list.toString());
        ;
    }

    @Test
    public void testFormat() {
        String string = "a%1$ddftry";
        System.out.println(String.format(string, 123));
    }

    @Test
    public void testListToSet() {
        List<String> list = Arrays.asList("a", "b", "c", "b");
        System.out.println(new HashSet<>(list));
    }


    @Test
    public void testThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 100; i++) {
            final int finalI = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(60 * 60);
                        System.out.println("complete !" + finalI);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        try {
            Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testClassObjectPoint() {
        Student a = new Student();
        a.setName("1");
        a.setNumber(1);
        Student b = a;

        a = new Student();
        a.setNumber(2);
        a.setName("2");
        System.out.println("a " + a);
        System.out.println("b " + b);
    }

    @Test
    public void testCombineListAndSet() {
        List<Integer> a = Arrays.asList(1, 2, 3);
        List<Integer> b = Arrays.asList(2, 3, 4);
        Set<Integer> combineSet = new HashSet<>(a);
        combineSet.addAll(b);
        System.out.println(combineSet);
    }

    @Test
    public void testContains() {
        String string = "aabb";
        String content = "aae23bb";
        System.out.println(content.contains(string));
    }

    @Test
    public void testRegex() {
        System.out.println(Pattern.matches("", "wowo"));
    }

    @Test
    public void testContainsNull() {
        try {
            System.out.println("saaa".contains(null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testReflect() throws Exception {

        Class<?> clazz = Class.forName("com.zylear.j2eelab.example.TestInnerClass");
        Constructor constructor = clazz.getConstructor();
        System.out.println(constructor.toGenericString());
        System.out.println(constructor.toString());
        System.out.println(constructor.getDeclaringClass());
        System.out.println(Arrays.asList(constructor.getDeclaredAnnotations()));
        System.out.println(clazz.getName());
        System.out.println(clazz.getSimpleName());
        System.out.println(clazz.getCanonicalName());
        System.out.println(Arrays.asList(clazz.getAnnotations()));
        System.out.println();
        System.out.println(clazz.isAnnotationPresent(Handle.class));
        System.out.println(clazz.isAnnotationPresent(PostConstruct.class));
        Handle annotation = clazz.getAnnotation(Handle.class);
        System.out.println(annotation.toString());


    }

    @Test
    public void testClassLoader() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources("com/zylear/boot");
        while (resources.hasMoreElements()) {
            URL u = resources.nextElement();
            System.out.println(u);
            System.out.println(u.getProtocol());
            System.out.println(u.getPath());
            System.out.println();
        }

    }


    @Test
    public void testReflection() throws ClassNotFoundException, NoSuchMethodException {
        System.out.println(this.getClass().getSimpleName());
        System.out.println(this.getClass().getName());
        System.out.println(this.getClass().getCanonicalName());
        System.out.println();
        for (Annotation annotation : this.getClass().getAnnotations()) {
            System.out.println(annotation);
        }
    }

    @Test
    public void fileP() {
        String path = "com/zylear/c.txt";
        File file = new File(path);
        System.out.println(file.getAbsolutePath());

    }

    @Test
    public void testInstanceof() {
        int r = new Random().nextInt(10);
        System.out.println("r:" + r);
        Father people;
        if (r % 2 == 0) {
            people = new Son();

        } else {
            people = new Daughter();
        }
        if (people instanceof Son) {
            System.out.println("yes");
            ((Son) people).showBoy();
            people.showName();
        }

        if (people instanceof Daughter) {
            System.out.println("daughter");
            ((Daughter) people).showGirl();
            people.showName();
        }
    }

    @Test
    public void testPatternMatcher() {
        String text = "wfdsfsf(89)";
        String regex = "(.*)(\\()(.*?)(\\))(.*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.matches()) {
            String count = matcher.group(3);
            System.out.println(count);
            //   return Integer.valueOf(count);
        }
    }

    @Test
    public void testJSONObjectGetAttribute() throws JSONException {
        JSONObject jsonObject = new JSONObject("{'aa':1}");
        System.out.println(jsonObject.getInt("aa"));
        System.out.println(jsonObject.get("ddd"));

        List a;
        Queue<String> queue = new ArrayDeque<>();
        //   d.poll();

    }


    @Test
    public void testSetSuccess() {
        Set<String> set = new HashSet<>();
        System.out.println(set.add("111"));
        System.out.println(set.add("222"));
        System.out.println(set.add("111"));
    }

    @Test
    public void testSetMaxSize() {
        String string = "今天今天今天今天今天今天今天今天今天今天今天今天今天今天今天今天今天今天今天今天今天今天今天今天今天今天今天今天今天今天今天今天今天";
        Set<String> set = new HashSet<>();
        try {
            int i = 0;
            while (true) {
                set.add(string + i);
                System.out.println(++i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String s;


        BitSet bitSet = new BitSet(2);

    }

    @Test
    public void testShowTime() {
        System.out.println(new Date(1522062614520L));
    }


    @Test
    public void testListRemove() {
        List<String> list = Arrays.asList("1", "2", "3");
//        for (String string : list) {
//            if ("2".equals(string)) {
//                list.remove(string);
//            }
//        }
        System.out.println(list.subList(1, 3));
    }

    boolean bool;

    @Test
    public void testDefaultBoolean() {

        System.out.println(bool);
    }

    @Test
    public void testGetEmptyList() {
        try {
            new ArrayList<>().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInteger() {
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        System.out.println(a == b);
        Integer c = Integer.valueOf(1);
        Integer d = Integer.valueOf(1);
        System.out.println(c == d);
        Integer e = Integer.valueOf(10000);
        Integer f = Integer.valueOf(10000);
        System.out.println(e == f);
    }

    @Test
    public void testEnum() {
        System.out.println(Myenum.aa.equals("aa"));
        System.out.println(Myenum.aa.toString().equals("aa"));

    }

    @Test
    public void testComplexGrepx() {
        String string = "";

        Map map = null;


    }

    @Test
    public void testObjectSet() {
        Set<Custom> set = new HashSet<>();
        set.add(new Custom("a", "1"));
        set.add(new Custom("a", "1"));
        set.add(new Custom("c", "3"));

        System.out.println(set);
        System.out.println(set.size());
    }


    @Test
    public void testLinkedHashSet() {
        Set<String> set = new LinkedHashSet<>();
        set.add("11");
        set.add("22");
        set.add("33");
        System.out.println(set);
    }

    @Test
    public void testEmptyRemove() {
        List<String> a = Collections.EMPTY_LIST;
        a.remove("d");
        System.out.println(1);
    }

    @Test
    public void testScheduledThreadPool() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("1 out");
                try {
                    Thread.sleep(200000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 3L, 3L, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("2 out");
            }
        }, 4L, 3L, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("3 out");
            }
        }, 5L, 3L, TimeUnit.SECONDS);


        while (true) {

        }
    }

    @Test
    public void testClone() {
        Student student = new Student();
        student.setName("name");
        student.setNumber(1);
        try {
            Student clone = student.clone();
            System.out.println(clone);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


    }

    Logger logger = LoggerFactory.getLogger(PureTest.class);


    @Test
    public void tesCachetThreadPool() throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
        for (int i = 0; i < 50; i++) {
            final int finalI = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {

                        Thread.sleep(30 * 1000 + finalI * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    logger.info("activity count:" + threadPoolExecutor.getActiveCount());
                    logger.info("queue size:" + threadPoolExecutor.getQueue().size());
                    logger.info("");
                }
            });


        }


        while (true) {
            Thread.sleep(2000);
            logger.info("activity count:" + threadPoolExecutor.getActiveCount());
            logger.info("queue size:" + threadPoolExecutor.getQueue().size());
            logger.info("");
        }


    }

    @Test
    public void testMaxInterger() {
        System.out.println(Integer.MAX_VALUE + 1);
    }


    @Test
    public void testDateToString() {
        System.out.println(new Date().toString());
    }


    @Test
    public void testGetIpByUrl() {
        try {
            System.out.println(InetAddress.getByName("spideradmin.changingedu.com"));
            System.out.println(InetAddress.getByName("spideradmin.changingedu.com"));
            System.out.println(InetAddress.getByName("spideradmin.changingedu.com"));
            System.out.println(InetAddress.getByName("spideradmin.changingedu.com"));

            System.out.println(InetAddress.getByName("www.baidu.com").getHostAddress());
            System.out.println(InetAddress.getByName("www.baidu.com"));
            System.out.println(InetAddress.getByName("www.baidu.com"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMapRemove() {

//
//        Map<String, String> map = new HashMap<>();
//        map.put("aa", "11");
//        map.put("bb", "22");
//        map.put("cc", "33");
//        String key = null;
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            if (entry.getValue().equals("22")) {
////                map.remove(entry.getKey());
//                key = entry.getKey();
//            }
//        }
//        if (key != null) {
//            map.remove(key);
//        }
//        System.out.println(map);

        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
//        objects.get(1);
        objects.remove(1);
        objects.iterator();

        LinkedList<Object> objects1 = new LinkedList<>();
        objects1.add(1);
        objects1.get(0);
        objects1.remove(1);

//        System.out.println(3>>1);
    }

    @Test
    public void TestIterator() {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next == 2) {
                iterator.remove();
            }
        }

        System.out.println(integers);
        System.out.println(iterator);
    }


    @Test
    public void testServerSocket() throws IOException {
        ServerSocket ss = new ServerSocket(7777);
        while (true) {
            final Socket socket = ss.accept();
            new Thread() {
                @Override
                public void run() {
                    System.out.println("connected");
                    while (true) {
                        BufferedReader br = null;
                        try {


                            br = new BufferedReader(new InputStreamReader(socket
                                    .getInputStream()));
//                            if ()
                            String s = br.readLine();
                            if (s != null) {
                                System.out.println("you input is : " + s);
                            }

                        } catch (IOException e) {
                            break;
                        }
                    }
                }
            }.start();


        }
    }

    @Test
    public void testSocket() throws IOException {
        Socket client = new Socket("127.0.0.1", 7777);
        System.out.println("connected");
        PrintWriter pw = new PrintWriter(client.getOutputStream());
        Scanner scanner = new Scanner(System.in);
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String string = "test";
        pw.write(string);
        pw.close();
        pw.close();
    }

    @Test
    public void testNio() {
//        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
//        byteBuffer.putDouble(3.32432);
//        Byte[] bytes = new Byte[]{12, 122};
//        System.out.println(byteBuffer.array());
//        System.out.println(bytes);

        System.out.println(~45654);


    }

    @Test
    public void testNioReadFile() throws IOException {
        FileChannel channel = new FileInputStream("D:\\practice\\j2eelab-one\\src\\test\\java\\com\\zylear\\j2eelab\\EnumTest.java").getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int read;
        byte[] bytes = new byte[1024];
        while (channel.read(byteBuffer) != -1) {
//            System.out.println("po:" + byteBuffer.position());
//            System.out.println("limit:" + byteBuffer.limit());
//            System.out.println("read:" + read);
            int position = byteBuffer.position();
            byteBuffer.flip();
            byteBuffer.get(bytes, 0, position);
            byteBuffer.clear();
            System.out.print(new String(bytes, 0, position));
        }
    }

    @Test
    public void testEnumEquals() {
        System.out.println(GroupConfigStatus.no_handle.toString().equals("no_handle"));
    }


    @Test
    public void testReflectConstruct() throws IllegalAccessException, InstantiationException {
        Class clazz = Son.class;
        Object o = clazz.newInstance();
    }

    @Test
    public void testBlockingQueue() throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();


        for (int i = 0; i < 1000; i++) {
            queue.put(i + "something after");
        }
        long startTime = System.currentTimeMillis();
        System.out.println(startTime);
        while (true) {
            if (queue.isEmpty()) {
                break;
            }
            String take = queue.take();
//            System.out.println(take);
        }
        System.out.println((System.currentTimeMillis() - startTime));

    }

    @Test
    public void timerTaskTest() {
        Timer timer = new Timer();
        final int outSign = 0;
        TimerTask timerTask = new TimerTask() {

            private int sign = outSign;

            @Override
            public void run() {
                System.out.println("in run current time millis:" + System.currentTimeMillis() / 1000 + "  " + sign);
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("in run current time millis:" + System.currentTimeMillis() / 1000 + "  " + sign);
                sign = sign + 1;
            }
        };

        timer.schedule(timerTask, 0, 3000);
        while (true) {

        }
    }

    @Test
    public void testPostConstruct() throws InterruptedException {
        Student student = new Student();


        Thread.sleep(5000);
        student.setName("aaa");
        Thread.sleep(5000);

    }

    @Test
    public void tsetEmpty() {
        ArrayList<String> strings = new ArrayList<>(3);
        System.out.println(strings.size());
    }


    @Test
    public void testCollectionsSort() {
        List<Long> list = Arrays.asList(2L, 6L, 10L);
        List<Long> link = new LinkedList<>(list);
        Collections.sort(link);
        System.out.println(link);
        link.add(0, 65L);
        System.out.println(link);
    }


    @Test
    public void testCalendar() throws Exception {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("");
        String YMDHMS = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(YMDHMS);
        Date date = formatter.parse("2018-09-30 24:02:01");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        if (hourOfDay >= 0 && hourOfDay < 8) {
            calendar.set(Calendar.HOUR_OF_DAY, 8);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            System.out.println(formatter.format(calendar.getTime()));

        } else if (hourOfDay >= 22 && hourOfDay < 24) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 8);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            System.out.println(formatter.format(calendar.getTime()));
        } else {
            System.out.println(formatter.format(date));
        }

    }

    @Test
    public void testMapKeySet() {
        Map<String, String> map = new HashMap<>();
        map.put("aaa", "sa");
        Set<String> s = map.keySet();
        System.out.println(map);
        System.out.println(s);
        s.remove("aaa");
        System.out.println(s);
        System.out.println(map);

        map.put("aaa", "sa");
        Set<String> ss = new HashSet<>(map.keySet());
        System.out.println(map);
        System.out.println(ss);
        ss.remove("aaa");
        System.out.println(ss);
        System.out.println(map);

    }


    @Test
    public void charAt() {
        System.out.println("{\"appPlatform\":\"win\",\"chatMsgBeans\":[{\"chatMode\":2,\"msgType\":1,\"msgId\":831,\"msgServerId\":\"4848135245.".indexOf("{"));
    }

    @Test
    public void c() {
        String s = "中文";

        char[] chars = s.toCharArray();
        char[] ss = {'\'', '地'};
        System.out.println((int) '地');

        System.out.println(chars);
    }

    @Test
    public void testReentrantLock() {
        final ReentrantLock reentrantLock = new ReentrantLock();


        final Data data = new Data();

        data.value = 1000;
        List<Thread> threads = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread() {

                @Override
                public void run() {
                    while (true) {

//                        reentrantLock.lockInterruptibly();//.lock();
                        reentrantLock.lock();
                        try {
                            if (data.value > 0) {
                                System.out.println(data.value);
                                try {
                                    Thread.sleep(10);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                data.value = data.value - 1;
                            }
                        } finally {
                            reentrantLock.unlock();
                        }


                    }

                }
            };
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    @Test
    public void testListNull() {
        List list = Collections.singletonList(null);
        for (Object object : list) {
            System.out.println(object);
            System.out.println(object.toString());
        }
    }

    @Test
    public void testReplaceAll() {
        System.out.println("dfdrandom%!%12".replaceAll("random%!%[0-9]*", "random%!%555"));
    }

    @Test
    public void testTrim() {
        String a = "sdfdf\r\n" +
                "wdff " +
                " \r\n  aqq19910763481\u202Ca" +
                "qq19910763481\u202C";

        String trim = a.trim();
        System.out.println(trim);
    }

    @Test
    public void souts() {
        List<String> strings = readString("D:\\test\\chat_log.txt");
        List<String> remover = new LinkedList<>();
        System.out.print("[");
        for (String string : strings) {
            if (string.matches("((^\"|^\\[|^<|^[0-9]).*)|(.*移出群聊.*)")) { //
                remover.add(string);
                continue;
            }

            System.out.println("\"" + removeCustom1(string).replace("\"", "\\\"") + "\",");
        }
        System.out.print("]");
        System.out.println();
        System.out.println();
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println();
        System.out.println();

        System.out.println(remover);

    }


    @Test
    public void removeCustom() {
        String string = "何妈[握手dsd]妈好[握手]wd";
        String regex = "\\[.*?]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            string = string.replace(matcher.group(0), "");
        }

        System.out.println(string);
    }

    public String removeCustom1(String string) {
        String regex = "\\[.*?]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            string = string.replace(matcher.group(0), "");
        }

        return string;
    }


    private static List<String> readString(String path) {

        List<String> list = new LinkedList<>();
        File file = new File(path);
        BufferedReader bufferedReader = null;
        try {

            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));
            String string;
            while ((string = bufferedReader.readLine()) != null && !StringUtils.isEmpty(string)) {
                list.add(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Test
    public void sou() {
        System.out.println(Integer.MAX_VALUE);

        try {
            FileInputStream fileInputStream = new FileInputStream("file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(2);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[3]);

    }

    @Test
    public void testBreak() {
        for (int i = 0; i < 10; i++) {

            switch (i) {
                case 2:
                    System.out.println("iiiiiiiiiiiiiiiii");
                    break;
                case 4:
                    System.out.println("eeeee");
                default:
                    System.out.println("defaut default");

            }

            System.out.println(i);
        }
    }

    @Test
    public void testArrayObject() throws JSONException {
        String s = "[1342,234.34,haoba]";
        JSONArray jsonArray = new JSONArray(s);
        System.out.println(jsonArray.getInt(0));
        System.out.println(jsonArray.getDouble(1));
        System.out.println(jsonArray.getString(2));

    }

    @Test
    public void testSub() {
        List list = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(list.subList(2, 4));
    }

    @Test
    public void showText() {
        String string = "4333741042@chatroom:\n<sysmsg type=\"sysmsgtemplate\">\n\t<sysmsgtemplate>\n\t\t<content_template type=\"tmpl_type_profile\">\n\t\t\t<plain><![CDATA[]]></plain>\n\t\t\t<template><![CDATA[\"$username$\"邀请你和\"$names$\"加入了群聊]]></template>\n\t\t\t<link_list>\n\t\t\t\t<link name=\"username\" type=\"link_profile\">\n\t\t\t\t\t<memberlist>\n\t\t\t\t\t\t<member>\n\t\t\t\t\t\t\t<username><![CDATA[richielsfy]]></username>\n\t\t\t\t\t\t\t<nickname><![CDATA[鑫爸爸1255]]></nickname>\n\t\t\t\t\t\t</member>\n\t\t\t\t\t</memberlist>\n\t\t\t\t</link>\n\t\t\t\t<link name=\"names\" type=\"link_profile\">\n\t\t\t\t\t<memberlist>\n\t\t\t\t\t\t<member>\n\t\t\t\t\t\t\t<username><![CDATA[wxid_gipw5c0dexk322]]></username>\n\t\t\t\t\t\t\t<nickname><![CDATA[赤脚罗汉]]></nickname>\n\t\t\t\t\t\t</member>\n\t\t\t\t\t\t<member>\n\t\t\t\t\t\t\t<username><![CDATA[q64238268]]></username>\n\t\t\t\t\t\t\t<nickname><![CDATA[妈妈-卞晓玲]]></nickname>\n\t\t\t\t\t\t</member>\n\t\t\t\t\t</memberlist>\n\t\t\t\t\t<separator><![CDATA[、]]></separator>\n\t\t\t\t</link>\n\t\t\t</link_list>\n\t\t</content_template>\n\t</sysmsgtemplate>\n</sysmsg>\n";
        System.out.println(string);
    }

    @Test
    public void testNull() {
        Integer i = null;
        System.out.println(i == 3);
    }

    @Test
    public void testAppenNull() {
        String a = null;
        StringBuffer buffer = new StringBuffer();

        buffer.append(a).append("xxx");
        System.out.println(buffer.toString());
    }

    @Test
    public void getCastInt() {
        long a = 21L;
        long b = 234L;
        long c = 2345L;
        System.out.println((int) a);
        System.out.println((int) b);
        System.out.println((int) c);
    }

    @Test
    public void testTryFinally() {
        try {
            try {
                System.out.println();
                throw new RuntimeException("xx");

            } finally {
                System.out.println("fi");
            }
        } catch (Exception e) {
            System.out.println("dfdf  " + e);
        }

    }

    @Test
    public void testEncrypt() throws Exception {
        String key = "1e/WkYyJNHkx4/HWDchRQ+AmE+qKONY+";

        String encryptData = "iFuhK8AJojZym/6T25hK6lv2IHgLG9rv";

        String decryptData = TripleDESUtil.decrypt(encryptData, key);
        System.out.println("解密: " + decryptData);

    }


    @Test
    public void testDecrypt() throws Exception {
        String key = "1e/WkYyJNHkx4/HWDchRQ+AmE+qKONY+";

        String source = "v1_7b0781cc32899053ffd0114755a41797d7f672840daec4552fef8bd3251b66da@stranger";

        String encryptData = TripleDESUtil.encrypt(source, key);
        System.out.println("加密: " + encryptData);

    }


    @Test
    public void getEnv() {
        Map<String, String> map = System.getenv();

//        System.out.println(map.get("Path"));
//        Properties properties = new Properties();
//        properties.setProperty("Path", "C:\\tmp");
        System.setProperty("Path", map.get("Path") + ";C:\\tmp");

        System.out.println();
        System.out.println();
        System.out.println(map.get("Path"));
//
//        for (Iterator<String> itr = map.keySet().iterator(); itr.hasNext(); ) {
//            String key = itr.next();
//            System.out.println(key + "=" + map.get(key));
//        }
    }

    @Test
    public void testThreadPoolException() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
//                try {
                System.out.println("q");
//                } catch (Exception e) {
                throw new RuntimeException("df");
//                }

//                System.out.println("3");
            }

        });


        Thread.sleep(60000);
    }

    @Test
    public void testRemovre() {
        String a = "{\"a\",\"a\"}";
        System.out.println(a.substring(a.indexOf("{")));
    }


    @Test
    public void testLongCast() {
        List<Integer> ii = new ArrayList<>();
        ii.add(1);

        List i = ii;

        List<Long> list = i;
        try {
            Long aLong = list.get(0);
            System.out.println(aLong.equals(2L));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void di() {
        List<String> list = new ArrayList<>(Arrays.asList(CodeString.string.split("\n")));
        Set<String> set = new LinkedHashSet(list);
        System.out.println(list.size());
        System.out.println(set.size());
        for (String s : set) {
            System.out.println(s);
//            set.add(s);
        }
    }

    @Test
    public void testIteratorRemoveSelf() {
        List<String> strings = new ArrayList<>();
        strings.add("1");
        Iterator<String> it = strings.iterator();
        strings.remove("1");
        it = strings.iterator();
        String name = it.next();
        System.out.println(name);

    }


    @Test
    public void oom() {
        List<String> list = new LinkedList<>();
        int i = 0;
        while (true) {
            list.add((String.valueOf(i++) + "lalala").intern());
        }
    }

    @Test
    public void testSerialize() throws Exception {

        Person person = new Person();
        person.setName("name");
        person.setNumber(3);

        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
                new File("D:/person.txt")));
        oo.writeObject(person);
    }

    @Test
    public void testSerializeReal() throws Exception {

        ObjectInputStream oo = new ObjectInputStream(new FileInputStream(
                new File("D:/person.txt")));
        Person person = (Person) oo.readObject();
        System.out.println(person);
    }


    @Test
    public void directOutOfMemoryError() throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(1024 * 1024);
        }
    }

    @Test
    public void testLoopTime() {
        int i = 500;
        long currentTimeMillis = System.currentTimeMillis();
        int a = 1;
        int b = 1;
        int c = 1;
        int d = 1;
        int e = 1;
        while (i-- > 0) {

            if (a > 0) {
                a = 1;
            }
            if (b < 0) {
                b = 1;
            }
            if (c > 0) {
                c = 1;
            }
            if (d < 0) {
                d = 1;
            }
            if (e > 0) {
                e = 1;
            }
            if (b < 0) {
                b = 1;
            }
        }

        System.out.println((System.currentTimeMillis() - currentTimeMillis) / 1000.0);

    }

    @Test
    public void testCharPlaceholder() {
        char a = '\u4e2d';
        char b = '\uD840';
        char c = '\uDC00';
        System.out.println((int) a);
        System.out.print(b);
        System.out.print(c);
        System.out.println();
        System.out.println(b + c);
        System.out.println(Integer.toHexString(a));
        System.out.println("\u4e2d\uD840\uDC00\u4e2d");

    }

    @Test
    public void mapValueTest() {
        Map<String, Student> map = new HashMap<>();

        Student student = new Student("a", 1);
        map.put("a", student);
        Student student2 = new Student("b", 2);
        Student c = student2;
        map.put("b", student2);
        Collection<Student> values = map.values();
        values.remove(c);


//        ArrayList<Student> list = new ArrayList<>(values);
//        list.get(0).setName("b");
//        System.out.println(list);
        System.out.println(map);
    }


    @Test
    public void testEncode() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String hex = "e4b8a5";
        int i = Integer.parseInt(hex, 16);
        System.out.println(i);
//        byte[] bytes = {0xe, 0x4, 0xb, 0x8, 0xa, 0x5};
        byte[] bytes = new byte[1024];
        FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\xiezongyu\\Pictures\\encode_test.txt"));
        int read;
        while ((read = fileInputStream.read()) != -1) {
            byteArrayOutputStream.write(bytes, 0, read);
        }

        System.out.println((byteArrayOutputStream.toString()));


//        Writer writer = new OutputStreamWriter(new FileOutputStream(new File("s")), "utf-8");

    }

    public static void main(final String[] args) {
        List<String> list = new LinkedList<>();
        long i = 0;
        while (true) {

            list.add((String.valueOf(i++) + "lalalalalaladwfdfwdf").intern());
        }

//        while (true) {
//            Enhancer enhancer = new Enhancer();
//
//            enhancer.setSuperclass(PureTest.class);
//            enhancer.setUseCache(false);
//            enhancer.setCallback(new MethodInterceptor() {
//                @Override
//                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//                    return methodProxy.invokeSuper(o, args);
//                }
//            });
//            enhancer.create();
//        }

    }

    //C:\Program Files (x86)\Common Files\Oracle\Java\javapath;C:\Program Files (x86)\python\Scripts\;C:\Program Files (x86)\python\;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Program Files\Java\jdk1.7.0_79\bin;C:\Program Files\Java\jdk1.7.0_79\jre\bin;C:\Program Files\TortoiseGit\bin;D:\Work\apache-maven-3.3.3\bin;D:\SDK\android-sdk-windows\platform-tools;D:\SDK\android-sdk-windows\tools;C:\Program Files (x86)\Appium\resources\app\node_modules\appium;C:\Program Files (x86)\GtkSharp\2.12\bin;C:\Program Files\dotnet\;C:\Program Files\nodejs\;C:\gradle\gradle-4.9\bin;C:\Program Files\Git\cmd;C:\python37;C:\python37\Scripts;C:\Users\xiezongyu\AppData\Local\Microsoft\WindowsApps;D:\SDK\android-sdk-windows\platform-tools;D:\SDK\android-sdk-windows\tools;C:\Program Files (x86)\Appium\node_modules\.bin;C:\Users\xiezongyu\AppData\Local\atom\bin;C:\Users\xiezongyu\AppData\Local\Programs\Fiddler;C:\Users\xiezongyu\AppData\Roaming\npm
}


enum Myenum {
    aa("aaa"),
    bb("bbb");

    private String tt;

    Myenum(String value) {
        this.tt = tt;
    }
}

enum GroupConfigStatus {
    unknown(-1),
    insert_new_group(1),
    insert_old_group(2),
    no_handle(3);

    private Integer value;

    GroupConfigStatus(Integer value) {
        this.value = value;
    }
}

interface Father {
    void showName();
}

class Son implements Father {

    @Override
    public void showName() {
        System.out.println("son name");
    }

    public void showBoy() {
        System.out.println("boy");
    }
}

class Daughter implements Father {


    @Override
    public void showName() {
        System.out.println("son name");
    }

    public void showGirl() {
        System.out.println("foot");
    }
}


class Student implements Cloneable {

    public Student() {
    }

    public Student(String name, Integer number) {
        this.name = name;
        this.number = number;
    }

    @PostConstruct
    public void init() {
        System.out.println("nnn" + name);
    }

    private String name;
    private Integer number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }

    @Override
    public Student clone() throws CloneNotSupportedException {
        Student student = null;
        try {
            Student s = (Student) student.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return student;
    }


}

abstract class AA {
    static int logger = 1;

    protected void show() {
        System.out.println(logger);
    }
}

class BB extends AA {
    static int logger = 2;


    public BB() {
        show();
    }
}

class Custom {
    public Custom(String name, String number) {
        this.name = name;
        this.number = number;

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((Custom) obj).name);
    }

    public String name;
    public String number;
}

class Data {
    public Integer value;


}


class Person implements Serializable {
    private static final long serialVersionUID = 6469374164513818595L;
    private String name;
    private Integer number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}


