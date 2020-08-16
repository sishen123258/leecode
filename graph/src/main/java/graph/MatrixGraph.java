package graph;

public class MatrixGraph {

    private Integer[] vertex;
    private Integer[][] matrix;
    private final Integer n;

    public MatrixGraph(Integer n){
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


    public Integer[][] getMatrix() {
        return matrix;
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


    public boolean addItem(int i,int j){

        if(i > n-1 || i<0){
            return false;
        }

        if(j > n-1 || j<0){
            return false;
        }

        this.matrix[i][j]=this.matrix[j][i]=1;
        return true;
    }

    @Override
    public String toString() {

        StringBuilder sb=new StringBuilder();

        for(Integer[] items:this.matrix){

            for (Integer node:items){
                sb.append(node);
                sb.append(",");
            }
            sb.append("\n");
        }


        return sb.toString();
    }

    public static void main(String[] args) {

        MatrixGraph lGraph=new MatrixGraph(3);
        Integer[] vertex = lGraph.vertex;

        System.out.println("vertex = " + vertex.length);

        Integer integer = lGraph.inDegree(0);
        System.out.println("integer = " + integer);

    }
}
