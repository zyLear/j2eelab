package com.zylear.j2eelab.alg;

import javax.validation.constraints.Max;
import java.util.Arrays;
import java.util.Collections;

public class MinHeap {

    private int[] data;

    public MinHeap(int[] data) {
        this.data = data;
//        buildHeap();
    }

    public void buildHeap() {
        for (int i = 0; i < data.length; i++) {
            heapify(i);
        }

    }

    public int getRoot() {
        return data[0];
    }

    public void setRoot(int count) {
        data[0] = count;
    }

    private void heapify(int i) {
        int leftIndex = getLeftIndex(i);
        int rightIndex = getRightIndex(i);

        int smallest = i;
        if (leftIndex < data.length && data[leftIndex] < data[smallest]) {
            smallest = leftIndex;
        }

        if (rightIndex < data.length && data[rightIndex] < data[smallest]) {
            smallest = rightIndex;
        }
        if (smallest == i) {
            return;
        }

        swap(i, smallest);
        heapify(smallest);
    }

    private void swap(int i, int smallest) {
        int tmp = data[i];
        data[i] = data[smallest];
        data[smallest] = tmp;

    }

    private int getLeftIndex(int i) {
        return i * 2 + 1;
    }

    private int getRightIndex(int i) {
        return i * 2 + 2;
    }

    public static void main(String[] args) {

        int length = 6;
        int[] a = {1, 2, 54, 7, 4, 87, 7, 5, 8, 67, 87, 56567, 34, 44, 66, 99, 100, 101, 104};

        int[] topK = new int[length];
        System.arraycopy(a, 0, topK, 0, length);
        MinHeap minHeap = new MinHeap(topK);
        minHeap.buildHeap();
        for (int i = length; i < a.length; i++) {
            if (a[i] > minHeap.getRoot()) {
                minHeap.setRoot(a[i]);
                minHeap.buildHeap();
            }
        }

        for (int i = 0; i < topK.length; i++) {
            System.out.println(topK[i]);
        }

    }


}
