package graph.DFS;

import java.util.ArrayList;
import java.util.List;

public class DogMaze {

    Integer m, n, t;

    String[][] maze;

    int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    int di, dj;

    int si, sj;

    public DogMaze(Integer m, Integer n, Integer t, String[][] maze) {
        this.m = m;
        this.n = n;
        this.t = t;

       this.maze = maze;
    }

    public void setDi(int di) {
        this.di = di;
    }

    public void setDj(int dj) {
        this.dj = dj;
    }

    public void setSi(int si) {
        this.si = si;
    }

    public void setSj(int sj) {
        this.sj = sj;
    }


    public boolean solution() {
        return dfs(si, sj, t);
    }

    public boolean dfs(Integer si, Integer sj, Integer t) {

        //判断停止条件

        if (si > m-1 || sj > n-1 || si < 0 || sj < 0) {
            return false;
        }

        String item = maze[si][sj];
        if(item.equals("X")){
            return false;
        }

        if(item.equals("D")&&t>=0){
            return true;
        }

        boolean tag=false;
        //从四个方向中选择一个进行搜索
        for(int[] d:dir){
            si+=d[0];
            sj+=d[1];

            tag=dfs(si,sj,t-1);

            if(tag){
                break;
            }
        }

        return tag;
    }


    public static void main(String[] args) {


        String[][] maze = {
                {".", ".", ".", "."},
                {".", "X", ".", "X"},
                {".", ".", ".", "D"}
        };

        DogMaze dogMaze = new DogMaze(3, 4, 5,maze);

        dogMaze.setSi(0);
        dogMaze.setSj(0);

        dogMaze.solution();
    }

}

/**
 * X: 墙壁，小狗不能进入
 * S: 小狗所处的位置
 * D: 迷宫的门
 * . : 空的方格
 */
class MazeTag {

    Integer m, n;
    String tag;

    public MazeTag(Integer m, Integer n, String tag) {
        this.m = m;
        this.n = n;
        this.tag = tag;
    }
}
