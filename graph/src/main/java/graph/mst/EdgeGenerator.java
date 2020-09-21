package graph.mst;

import graph.mst.bean.Edge;

import java.util.ArrayList;
import java.util.List;

public class EdgeGenerator {

    public static List<Edge> build(){

        List<Edge> edges=new ArrayList<>();
        edges.add(new Edge(1,2, 3));
        edges.add(new Edge(1,3, 2));
        edges.add(new Edge(2,4, 4));
        edges.add(new Edge(3,4, 5));
        edges.add(new Edge(3,5, 6));
        edges.add(new Edge(4,5, 7));

        return edges;
    }


}
