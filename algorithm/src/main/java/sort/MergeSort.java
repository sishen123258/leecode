package sort;

public class MergeSort {

    public static void main(String[] args) {

        int[] arr={5,4,3,2,1};
        int[] tmp=new int[arr.length];

        mergeSort(arr,0,arr.length-1,tmp);

        for (int i = 0; i < arr.length; i++) {
            System.out.println("i = " + arr[i]);
        }
    }

    private static void mergeSort(int[] arr, int low, int high, int[] tmp) {

        if(low<high){
            int mid=(low+high)/2;
            mergeSort(arr,low,mid,tmp);
            mergeSort(arr,mid+1,high,tmp);
            merge(arr,low,mid,high,tmp);
        }


    }

    private static void merge(int[] arr, int low, int mid, int high, int[] tmp) {

        int i=0;

        int j=low;
        int k=mid+1;

        while (j<=mid && k<=high){
            if(arr[j]<arr[k]){
                tmp[i++]=arr[j++];
            }else{
                tmp[i++]=arr[k++];
            }
        }

        while (j<=mid){
            tmp[i++]=arr[j++];
        }

        while (k<=high){
            tmp[i++]=arr[k++];
        }

        for(int t=0;t<i;t++){
            arr[low+t]=tmp[t];
        }

    }


}
