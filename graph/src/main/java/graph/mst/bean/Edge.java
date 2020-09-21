package graph.mst.bean;

public class Edge implements Comparable<Edge>{

    private Integer src;
    private Integer dst;
    private Integer weight;


    public Edge(Integer src, Integer dst, Integer weight) {
        this.src = src;
        this.dst = dst;
        this.weight = weight;
    }

    public Integer getSrc() {
        return src;
    }

    public Integer getDst() {
        return dst;
    }

    public Integer getWeight() {
        return weight;
    }


    @Override
    public int compareTo(Edge o) {
        return this.weight-o.weight;
    }
}
