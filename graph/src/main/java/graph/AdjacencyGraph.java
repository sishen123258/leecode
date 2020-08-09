package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdjacencyGraph {


    Node[] nodes;

    Integer nodeNum;


    public void initNodes(int n) {
        setNodeNum(n);
        nodes = new Node[n];
    }


    public void setNodeNum(Integer nodeNum) {
        this.nodeNum = nodeNum;
    }


    public Integer indegree(Integer n) {

        Node node = this.nodes[n - 1];

        int num = 0;

        if (node != null) {

            ArcNode in = node.in;

            while (in != null) {
                num++;
                in = in.next;
            }
        }

        return num;
    }

    public Integer outdegree(int n) {

        Node node = this.nodes[n - 1];

        int num = 0;

        if(node!=null){

            ArcNode out = node.out;

            while (out!=null) {
                num++;
                out = out.next;
            }
        }

        return num;
    }


    private void createGraph(List<Edge> edges) {

        for (Edge edge : edges) {

            addInEdge(edge);
            addOutEdge(edge);
        }
    }

    private void addOutEdge(Edge edge) {

        Node node = this.nodes[edge.arraySrcIndex()];

        if (node == null) {
            node = new Node(edge.src);
            this.nodes[edge.arraySrcIndex()] = node;
        }

        if (node.out == null) {
            node.out = new ArcNode(edge.dst, null);
        } else {

            ArcNode tmpOut = node.out;

            while (tmpOut.next != null) {
                tmpOut = tmpOut.next;
            }

            tmpOut.next = new ArcNode(edge.dst, null);
        }


    }

    private void addInEdge(Edge edge) {

        Node node = this.nodes[edge.arrayDstIndex()];

        if (node == null) {
            node = new Node(edge.dst);
            this.nodes[edge.arrayDstIndex()] = node;
        }

        if (node.in == null) {
            node.in = new ArcNode(edge.src, null);
        } else {
            ArcNode tmpIn = node.in;

            while (tmpIn.next != null) {
                tmpIn = tmpIn.next;
            }

            tmpIn.next = new ArcNode(edge.src, null);
        }

    }

    public static void main(String[] args) {

        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge(1, 2));
        edges.add(new Edge(2, 3));

        AdjacencyGraph graph = new AdjacencyGraph();
        graph.initNodes(3);

        graph.createGraph(edges);

        System.out.println("graph = " + Arrays.asList(graph.nodes));

        Integer indegree = graph.indegree(2);

        Integer outdegree = graph.outdegree(3);

        System.out.println("indegree = " + indegree);
        System.out.println("outdegree = " + outdegree);
    }


}

class ArcNode {

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
}


class Node {

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
}


class Edge {
    Integer src;
    Integer dst;

    public Integer arraySrcIndex() {
        return this.src - 1;
    }

    public Integer arrayDstIndex() {
        return dst - 1;
    }

    public Edge(Integer src, Integer dst) {
        this.src = src;
        this.dst = dst;
    }
}