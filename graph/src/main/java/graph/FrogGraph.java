package graph;

import java.util.ArrayList;
import java.util.List;

public class FrogGraph {

    private List<Integer> series;

    public FrogGraph(List<Integer> series) {
        this.series = series;
    }

    public boolean havelHakimi(List<Integer> nums){
        if(nums.size()<=0){
            return true;
        }
        nums.sort(null);

        Integer first = nums.get(0);
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


}
