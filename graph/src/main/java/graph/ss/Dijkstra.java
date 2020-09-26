package graph.ss;

import graph.mst.EdgeGenerator;
import graph.mst.bean.Edge;

import java.util.List;

public class Dijkstra {

    private Integer[] s;
    private Integer[] dist;
    private Integer[] path;
    private Integer nodesNum;
    private Integer[][] edges;

    public Dijkstra(int n) {
        this.s = new Integer[n];
        this.dist = new Integer[n];
        this.path = new Integer[n];

        this.edges = new Integer[n][n];

        this.nodesNum = n;
        initEdges();
    }

    private void initEdges() {

        List<Edge> edgeList = EdgeGenerator.build();

        for (Edge edge : edgeList) {
            this.edges[edge.getSrc()][edge.getDst()] = this.edges[edge.getDst()][edge.getSrc()] = edge.getWeight();
        }
    }

    public void search(Integer n) {

        this.dist = this.edges[n];

        for (int i = 1; i < this.nodesNum; i++) {

            int min = Integer.MAX_VALUE;
            Integer u = n;

            for (int j = 1; j < this.nodesNum; j++) {
                Integer item = this.dist[j];
                if (item != null && min > item) {
                    min = item;
                    u = j;
                }
            }

            s[u] = 1;

            if (u != -1) {

                for (int k = 1; k < this.nodesNum; k++) {
                    Integer dk = this.dist[k];
                    int newDistU = this.edges[u][k] + this.dist[u];
                    if (s[k]!=1&&newDistU < dk) {
                        this.dist[k]=newDistU;
                        this.path[k] = u;
                    }
                }
            }
        }

    }

}
