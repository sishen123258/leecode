package graph.mst;

import graph.mst.bean.Edge;

import java.util.Arrays;
import java.util.List;

public class PrimMST {

    private List<Edge> edges = EdgeGenerator.build();

    private Integer[][] matrix;
    private Integer[] lowCost;
    private Integer[] nearest;

    private Integer n;

    public PrimMST(int n) {
        this.n = n;
        this.lowCost = new Integer[n];
        this.nearest = new Integer[n];

        matrix = new Integer[n][n];
        initArrays();
    }

    private void initArrays() {
        initMatrix();

    }

    private void initMatrix() {

        for (Edge edge : edges) {
            matrix[edge.getSrc()][edge.getDst()] = matrix[edge.getDst()][edge.getSrc()] = edge.getWeight();
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                } else if (matrix[i][j] == null) {
                    matrix[i][j] = matrix[j][i] = Integer.MAX_VALUE;
                }
            }
        }
    }

    public void printMatrix(){

        StringBuilder sb=new StringBuilder();
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                sb.append(matrix[i][j]);
                sb.append(",");
            }
            sb.append(System.lineSeparator());
        }

        System.out.println(sb);
    }

    public void prim(int x) {
        Integer weight=0;

        for (int i = 1; i < this.lowCost.length; i++) {
            this.lowCost[i] = this.matrix[x][i];
            this.nearest[i] = x;
        }

        printArrays();

        this.nearest[x] = -1; //

        for (int i = 1; i < n; i++) {

            int min = Integer.MAX_VALUE;
            int v = -1;

            for (int j = 1; j < n; j++) {
                if (this.nearest[j] != -1 && this.lowCost[j] < min) {
                    v = j;
                    min = this.lowCost[j];
                }
            }

            if(v!=-1){ //表示找到权值最小的边
                System.out.println("v:"+v+" lowCost[v]:"+lowCost[v]+" nearest[v]:"+nearest[v]);

                this.nearest[v]=-1;
                weight+=lowCost[v];

                //update lowcost and nearest array
                for(int k=1;k<n;k++){
                    if(this.nearest[k]!=-1&&this.matrix[v][k]<this.lowCost[k]){
                        this.lowCost[k]=this.matrix[v][k];
                        this.nearest[k]=v;
                    }
                }
            }
        }

        System.out.println("weight = " + weight);
    }

    private void printArrays() {
        System.out.println("lowCost = " + Arrays.asList(lowCost));
        System.out.println("nearest = " + Arrays.asList(nearest));
    }

    public static void main(String[] args) {

        PrimMST primMST = new PrimMST(6);

        primMST.printMatrix();

        primMST.prim(1);
    }

}
