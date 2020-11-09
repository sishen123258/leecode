package sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuickSort2 {

    public static void main(String[] args) {

        List<Integer> integers = Arrays.asList(2, 1, 5, 4, 3);

//        Collections.shuffle(integers);
//        System.out.println("integers = " + integers);

        sort(integers, 0, integers.size() - 1);

        System.out.println("arr = " + integers);

    }

    private static void sort(List<Integer> arr, int start, int end) {

        if (start >= end) return;

        int partition = partition(arr, start, end);

        System.out.println("partition = " + partition);

        sort(arr, start, partition - 1);
        sort(arr, partition + 1, end);

    }

    private static int partition(List<Integer> arr, int start, int end) {

        int value=arr.get(start);

        int i=start;
        int j=end+1;

        while (true){

            while (arr.get(++i)<value){
                if(i==end) break;
            }

            while (arr.get(--j)>value){
                if(j==start) break;
            }

            if(i>=j) break;

            exchange(arr,i,j);
        }

        exchange(arr,start,j);

        return j;
    }

    private static void exchange(List<Integer> arr, int i, int j) {

        int temp=arr.get(i);
        arr.set(i,arr.get(j));
        arr.set(j,temp);
    }

}
