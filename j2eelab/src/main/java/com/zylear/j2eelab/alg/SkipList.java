package com.zylear.j2eelab.alg;

import java.util.concurrent.ConcurrentSkipListMap;

public class SkipList {

    public static void main(String[] args) {
        ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap();

        concurrentSkipListMap.put("sdf", "dsf");
    }

    private SkipListNode head;


    public void add(Integer value) {

    }

    public Integer find(Integer value) {


        return null;
    }


    public static class SkipListNode{

        private Integer value;
        private Integer level;
        private SkipListNode nextNode;
        private SkipListNode nextLevelNode;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public SkipListNode getNextNode() {
            return nextNode;
        }

        public void setNextNode(SkipListNode nextNode) {
            this.nextNode = nextNode;
        }

        public SkipListNode getNextLevelNode() {
            return nextLevelNode;
        }

        public void setNextLevelNode(SkipListNode nextLevelNode) {
            this.nextLevelNode = nextLevelNode;
        }
    }

}
