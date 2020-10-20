package sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Quick {


    public static void main(String[] args) {

        List<Integer> input = Arrays.asList(7, 8, 4, 5, 6, 3, 2,2,8);

        Collections.shuffle(input);

        System.out.println("input1 = " + input);

        quickSort(input,0,input.size()-1);

        System.out.println("input2 = " + input);
    }

    private static void quickSort(List<Integer> input, int start, int end) {

        if(start>=end){ //TODO
            return;
        }

        int partition = partition(input, start, end);

        quickSort(input,start,partition-1);

        quickSort(input,partition+1,end);

    }

    private static int partition(List<Integer> input, int start, int end) {

        int i=start;
        int j=end+1;

        int v=input.get(start);

        while (true){

            while (input.get(++i)<v){
                if(i==end) break;
            }

            while (input.get(--j)>v){
                if(j==start) break;
            }

            if(i>=j) break;

            exchange(input,i,j);
        }

        exchange(input,start,j);

        return j; //  为什么必须是j，因为指向的数字是小于v的
    }

    private static void exchange(List<Integer> input, int i, int j) {
        int tmp=input.get(i);
        input.set(i,input.get(j));
        input.set(j,tmp);
    }


    private static void deepHash() {
        Integer [] arr1={1,2,3};
        Integer [] arr2={1,3,2};

        int hash1 = Arrays.deepHashCode(arr1);
        int hash2 = Arrays.deepHashCode(arr2);


        System.out.println("hash1 = " + hash1);
        System.out.println("hash2 = " + hash2);
    }


}
