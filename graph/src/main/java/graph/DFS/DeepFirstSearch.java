package graph.DFS;

import graph.AdjacencyGraph;
import graph.ArcNode;
import graph.MatrixGraph;

import java.util.HashSet;
import java.util.Set;

public class DeepFirstSearch {

    public Set<Integer> visited = new HashSet<>();

    public void search(MatrixGraph mg, Integer i) {

        // 获得起始点的关联节点
        // 循环遍历关联节点
        // 打印 并将其置于访问过的set中
        if (!visited.contains(i)) {
            System.out.println(i);
            visited.add(i);
            Integer[][] matrix = mg.getMatrix();
            Integer[] connectedNodes = matrix[i];

            for (int j = 0; j < connectedNodes.length; j++) {

                Integer isConnectedNode = connectedNodes[j];

                if (isConnectedNode != 0 && !visited.contains(j)) {
                    search(mg, j);
                }

            }
        }
    }

    public void search(AdjacencyGraph ad, Integer i) {

        if (i >= 0 && i < ad.nodeNum) {

            if (!visited.contains(i)) {

                visited.add(i);
                System.out.println(i);
                ArcNode node = ad.getNodes()[i].getOut();

                while (node!=null){

                    search(ad,node.getId()-1);
                    node=node.getNext();
                }
            }
        }
    }


    public static void main(String[] args) {

//        testMatrixGraph();
        testAdjacencyGraph();
    }

    private static void testAdjacencyGraph() {

        AdjacencyGraph adjacencyGraph = AdjacencyGraph.getAdjacencyGraph();

        DeepFirstSearch dfs = new DeepFirstSearch();
        dfs.search(adjacencyGraph, 0);
    }

    private static void testMatrixGraph() {

        MatrixGraph mg = new MatrixGraph(5);

        mg.addItem(0, 1);
        mg.addItem(0, 2);
        mg.addItem(1, 3);
        mg.addItem(2, 3);
        mg.addItem(3, 4);

        System.out.println(mg);

        DeepFirstSearch dfs = new DeepFirstSearch();

        dfs.search(mg, 1);
    }


}
