package graph.mst.ufs;

public class UnionFindSet {

    private Integer[] parents;

    public Integer[] getParents() {
        return parents;
    }

    public UnionFindSet(Integer n) {

        this.parents = new Integer[n];

        for (int i = 0; i < n; i++) {
            this.parents[i] = -1;
        }
    }


    public Integer find(int x) {//查找并返回结点x 所属集合的根结点

        int s = x; //查找位置

        //查找到根节点
        while (parents[s] >= 0) {
            s = parents[s];
        }

        //路径压缩，使后续查找加速
        while (s != x) {
            int tmp = parents[x];
            parents[x] = s;
            x = tmp;
        }

        return s;
    }

    //R1 和R2 是两个元素，属于两个不同的集合，现在合并这两个集合
    public void union(int R1, int R2) {

        int rootNode1 = find(R1);
        int rootNode2 = find(R2);

        int parentValue = parents[rootNode1] + parents[rootNode2];

        //如果R2 所在树结点个数 > R1 所在树结点个数
        //注意parent[r1]和parent[r2]都是负数
        if (parents[rootNode1] > parents[rootNode2]) {

            //将根结点r1 所在的树作为r2 的子树(合并)
            parents[rootNode1]=rootNode2;
            parents[rootNode2]=parentValue;
        }else{
            parents[rootNode2]=rootNode1;
            parents[rootNode1]=parentValue;
        }
    }


    public static void main(String[] args) {

        int i = 1000000000;
        Integer j = 1000000000;

        System.out.println(j == i);

    }

}
