package graph;

public class LGraph {

    private Integer[] vertex;
    private Integer[][] matrix;
    private final Integer n;


    private LGraph(Integer n){
        this.n=n;
        this.vertex=new Integer[n];
        this.matrix=new Integer[n][n];
        initGraph();
    }

    private void initGraph() {
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                matrix[i][j]=0;
            }
        }
    }

    private Integer outDegree(int i){
        if(i > n-1 || i<0){
            return -1;
        }

        Integer inDegree=0;
        for(Integer item:matrix[i]){
            if(item==1)
                inDegree+=1;
        }

        return inDegree;
    }

    private  Integer inDegree(int j){
        if(j > n-1 || j<0){
            return -1;
        }

        int degree=0;
        for (Integer[] item:this.matrix){
            if(item[j]==1)
                degree+=1;
        }
        return degree;
    }


    public static void main(String[] args) {

        LGraph lGraph=new LGraph(3);
        Integer[] vertex = lGraph.vertex;

        System.out.println("vertex = " + vertex.length);

        Integer integer = lGraph.inDegree(0);
        System.out.println("integer = " + integer);

    }
}
