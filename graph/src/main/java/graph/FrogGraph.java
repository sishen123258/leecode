package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrogGraph {


    private List<Node> nodes;

    boolean flag = true;

    private Integer[][] matrix;

    private Integer n;


    public FrogGraph() {
    }


    private void initMatrix() {

        matrix = new Integer[n][n];
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    private void buildGraph() {

        //Sort
        //first=n
        //后续n个点度数-1
        //判断能不能构成图
        //记录到matrix里面

        int i = 0;
        List<Node> tmpNodes = this.nodes.subList(i, n);


        for (i = 0; i < n & flag; i++) {


            Collections.sort(tmpNodes);

            Node firstNode = tmpNodes.get(0);

            if (firstNode.degree > tmpNodes.size() - 1) {
                flag = false;
            }

            for (int j = 1; j <= firstNode.degree & flag; j++) {

                Node nextJNode = tmpNodes.get(j);

                if (nextJNode.degree <= 0) {
                    flag = false;
                }

                nextJNode.degree -= 1;

                matrix[firstNode.id-1][nextJNode.id-1] = matrix[nextJNode.id-1][firstNode.id-1] = 1;
            }


            tmpNodes = tmpNodes.subList(1, tmpNodes.size());
        }

        if (flag) {
            StringBuilder sb = new StringBuilder();

            for (int r = 0; r < n; r++) {
                for (int j = 0; j < n; j++) {
                    Integer obj = matrix[r][j];
                    if (obj == null) {
                        sb.append(0);
                    } else {
                        sb.append(obj);
                    }
                    sb.append(",");
                }
                sb.append("\n");
            }
            System.out.println(sb);
        }

    }

    class Node implements Comparable<Node> {
        Integer id;
        Integer degree;

        public Node(Integer id, Integer degree) {
            this.id = id;
            this.degree = degree;
        }

        @Override
        public int compareTo(Node o) {
            return o.degree - this.degree;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    ", degree=" + degree +
                    '}';
        }
    }


    public static void main(String[] args) {

        List<Node> nodes = new ArrayList<>();


        FrogGraph frogGraph = new FrogGraph();

        nodes.add(frogGraph.new Node(1, 2));
        nodes.add(frogGraph.new Node(2, 1));
        nodes.add(frogGraph.new Node(3, 1));
        nodes.add(frogGraph.new Node(4, 2));

        frogGraph.setNodes(nodes);
        frogGraph.setN(nodes.size());
        frogGraph.initMatrix();

        frogGraph.buildGraph();


    }


}
