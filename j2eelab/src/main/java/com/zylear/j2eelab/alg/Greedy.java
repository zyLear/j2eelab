package com.zylear.j2eelab.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Greedy {

    /**
     * 　　乘船问题，有n个人，第i个人重量为wi。每艘船的最大载重量均为C，且最多只能乘两个人。用最少的船装载所有人。求需要最少的船的数量。
     * <p>
     * 　　贪心策略：考虑最轻的人i，如果每个人都无法和他一起坐船（重量和超过C），则唯一的方案是每个人坐一艘。否则，他应该选择能和他一起坐船的人中最重的一个j。
     *
     * @param args
     */
    public static void main(String[] args) {
//        example1();

        example2();
    }

    private static void example2() {
        int[] w = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] v = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        double c = 50.0;

        List<Obj> list = new ArrayList<>(w.length);
        for (int i = 0; i < w.length; i++) {
            list.add(new Obj(i, v[i], w[i]));
        }
        list.sort((o1, o2) -> {
//            if (o2.price.equals(o1.price)) {
//                return o1.w.compareTo(o2.w);
//            } else {
            return o2.price.compareTo(o1.price);
//            }
        });
        double remain = c;
        double maxV = 0.0;
        for (Obj obj : list) {
            if (obj.w < remain) {
                maxV += obj.v;
                remain -= obj.w;
            }else {
                maxV += remain * obj.price;
                break;
            }
        }

        System.out.println(maxV);
    }


    public static class Obj {

        public Obj(int i, int v, int w) {
            this.i = i;
            this.v = v;
            this.w = w;
            this.price = v / (double) w;
        }

        public int i;
        public Double price;
        public int v;
        public Integer w;

    }

    private static void example1() {
        int[] w = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int n = w.length;
        int c = 10;

        Arrays.sort(w);
        int cntOfPerson = n;
        int cntOfBoat = 0;
        int p1 = 0;
        int p2 = n - 1;
        while (cntOfPerson > 0) {
            if (w[p1] + w[p2] > c) {
                p2--;
                cntOfPerson--;
                cntOfBoat++;
            } else {
                p1++;
                p2--;
                cntOfPerson -= 2;
                cntOfBoat++;
            }
        }
        System.out.println(cntOfBoat);
    }


}
