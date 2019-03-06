package com.zylear.j2eelab.hook;

import org.apache.commons.lang3.RandomUtils;

import java.util.*;

/**
 * Created by xiezongyu on 2018/11/15.
 */
public class GenerateCard {

    public static final char[] NUMBERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static final char[] CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static final String INSERT_TEMPLATE = "insert into t_card_info(card_number,card_password,months,is_used,is_deleted,create_time,remark) values('%s','%s',%s,0,0,now(),'0');";
    public static final String SHOW_TEMPLATE = "%s  卡号：%s   卡密：%s";


    public static void main(String[] args) {
//        generateCard("181224", "01", "1", 40, 80);   //月卡
//        generateCard("181224", "07", "70", 40, 20);   //周卡
//        generateCard("181229", "11", "11", 40, 0);   //辅助月卡
        generateCard("181229", "17", "170", 40, 0);   //辅助周卡


    }

    private static void generateCard(String prefix, String suffix, String months, Integer count, Integer offset) {
        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < count; i++) {
            StringBuilder stringBuilder = new StringBuilder(prefix);
            StringBuilder password = new StringBuilder();
            for (int j = 0; j < 6; j++) {
                stringBuilder.append(NUMBERS[RandomUtils.nextInt(0, NUMBERS.length)]);
            }

            for (int j = 0; j < 16; j++) {
                password.append(CHARS[RandomUtils.nextInt(0, CHARS.length)]);
            }
            stringBuilder.append(suffix);
            result.put(stringBuilder.toString(), password.toString());
        }
        System.out.println(result.size());
        System.out.println();
        for (Map.Entry<String, String> entry : result.entrySet()) {
            System.out.println(String.format(INSERT_TEMPLATE, entry.getKey(), entry.getValue(), months));
        }

        System.out.println();
        System.out.println();
        System.out.println();

        int number = 1;
        for (Map.Entry<String, String> entry : result.entrySet()) {
            System.out.println(String.format(SHOW_TEMPLATE, offset + number++, entry.getKey(), entry.getValue()));
        }
    }
}
