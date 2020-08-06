package graph;

import java.util.*;

public class FrogGraph {

    private List<Integer> series;

    private Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    };

    public FrogGraph(List<Integer> series) {
        this.series = series;
    }

    public boolean havelHakimi(List<Integer> nums){
        if(nums.size()<=0){
            return true;
        }
        nums.sort(comparator);

        Integer first = nums.get(0);

        if(first==0){
            return true;
        }

        if(first>nums.size()-1){
            return false;
        }

        List<Integer> arrs=new ArrayList<>();
        for(int i=1;i<nums.size();i++){
            Integer value = nums.get(i);
            value-=1;
            if(value<0){
                return false;
            }
            arrs.add(value);
        }

        return havelHakimi(arrs);
    }

    public static void main(String[] args) {
        FrogGraph frogGraph=new FrogGraph(Arrays.asList(2,1,1));

        boolean b = frogGraph.havelHakimi(frogGraph.series);
        System.out.println("b = " + b);


    }

}
