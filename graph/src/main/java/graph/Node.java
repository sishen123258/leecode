package graph;

public class Node {

    Integer id;
    ArcNode out;
    ArcNode in;

    public Node(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                '}';
    }

    public ArcNode getOut() {
        return out;
    }

    public Integer getId() {
        return id;
    }

    public ArcNode getIn() {
        return in;
    }
}