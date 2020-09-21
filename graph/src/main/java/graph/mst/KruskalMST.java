package graph.mst;

import graph.mst.bean.Edge;
import graph.mst.ufs.UnionFindSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KruskalMST {

    private List<Edge> edges;
    private UnionFindSet ufs;
    private Integer nodeNum;

    public KruskalMST(Integer n, Integer nodesNum) {
        this.edges=new ArrayList<>(n);
        ufs=new UnionFindSet(nodesNum);
        this.nodeNum=nodesNum;
    }

    private void initEdges() {
        this.edges=EdgeGenerator.build();
    }

    private void doKruskal() {
        int wight=0;
        int num=0;

        Collections.sort(edges);

        for(Edge edge:edges){
            int left = edge.getSrc() - 1;
            Integer rootSrc = ufs.find(left);
            int right = edge.getDst() - 1;
            Integer rootDst = ufs.find(right);

            System.out.println(String.format("left:%s rootLeft:%s right:%s rootRight:%s",left,rootSrc,right,rootDst));

            if(rootSrc!=rootDst){
                //System.out.println("edge.getWeight() = " + edge.getWeight());
                wight+=edge.getWeight();
                num++;
                ufs.union(left,right);
                System.out.println(Arrays.asList(ufs.getParents()));
            }

            if(num>=nodeNum-1) break;
        }

        System.out.println("wight = " + wight);
        System.out.println("num = " + num);
    }

    public static void main(String[] args) {

        KruskalMST mst=new KruskalMST(5,5);
        mst.initEdges();

        mst.doKruskal();
    }
}
