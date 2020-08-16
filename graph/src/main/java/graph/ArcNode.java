package graph;

public class ArcNode {

    Integer id;
    ArcNode next;

    public ArcNode(Integer id, ArcNode next) {
        this.id = id;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ArcNode{" +
                "id=" + id +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public ArcNode getNext() {
        return next;
    }
}