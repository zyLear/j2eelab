package com.zylear.j2eelab.pass;

import com.zylear.j2eelab.pass.main.ClientConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiezongyu on 2019/2/20.
 */
public class Json {

    public Map hashMap = new HashMap();
    public List list = new ArrayList();
    public String s;

    public Json(String str) {
        this.s = str;
        init();
    }

    public String get(String str) {
        return (String) this.hashMap.get(str);
    }

    public String getString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.list.size() - 1; i++) {
            String str = (String) this.list.get(i);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append("%!%");
            stringBuilder2.append(this.hashMap.get(str));
            stringBuilder2.append("%!!%");
            stringBuilder.append(stringBuilder2.toString());
        }
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(this.list.get(this.list.size() - 1));
        stringBuilder3.append("%!%");
        stringBuilder3.append(this.hashMap.get(this.list.get(this.list.size() - 1)));
        stringBuilder.append(stringBuilder3.toString());
        this.s = stringBuilder.toString();
        lock();
        return this.s;
    }

    public void init() {
        unlock();
        for (String split : this.s.split("%!!%")) {
            String[] split2 = split.split("%!%");
            this.hashMap.put(split2[0], split2[1]);
            this.list.add(split2[0]);
        }
    }

    public void lock() {
        char[] toCharArray = this.s.toCharArray();
        for (int i = 0; i < toCharArray.length; i++) {
            toCharArray[i] = (char) ((char) (toCharArray[i] ^ 2));
        }
        this.s = new String(toCharArray);
    }

    public void put(String str, String str2) {
        this.hashMap.put(str, str2);
        this.list.add(str);
    }

    public void unlock() {
        char[] toCharArray = this.s.toCharArray();
        for (int i = 0; i < toCharArray.length; i++) {
            toCharArray[i] = (char) ((char) (toCharArray[i] ^ 2));
        }
        this.s = new String(toCharArray);
    }



    public static void main(String[] args) {
        String string = "amooclf'#'omwlv\"/m\"pu.pgomwlv\"-%!%amooclf'#'po\"/pd\"-fcvc-nmacn-vor-(%!%amooclf'#'uegv\"/R\"-fcvc-nmacn-vor\";6,3;3,53,021-RjmglkzMQRcqqFgvgavkml,qj%!%amooclf'#'ajomf\"577\"-fcvc-nmacn-vor-RjmglkzMQRcqqFgvgavkml,qj%!%amooclf'#'qj\"-fcvc-nmacn-vor-RjmglkzMQRcqqFgvgavkml,qj%!%amooclf'#'GLF";
        System.out.println(ClientConnection.unlock(string).replace("'#'","\n"));
    }
}