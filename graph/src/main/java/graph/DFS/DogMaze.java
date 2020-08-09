package graph.DFS;

import java.util.ArrayList;
import java.util.List;

public class DogMaze {

    Integer m,n,t;


    String[][] maze;

    public DogMaze(Integer m, Integer n, Integer t) {
        this.m = m;
        this.n = n;
        this.t = t;

        maze=new String[m][n];
    }

    public void initMaze(List<MazeTag> mazeTags){

        for(MazeTag mt:mazeTags){
            maze[mt.m][mt.n]=mt.tag;
        }

    }


    public boolean solution(){



        return false;
    }





    public static void main(String[] args) {
        List<MazeTag> mazeTags=new ArrayList<>();

        DogMaze dogMaze = new DogMaze(3,4,5);
        dogMaze.initMaze(mazeTags);

        dogMaze.solution();
    }

}

/**
 * X: 墙壁，小狗不能进入
 * S: 小狗所处的位置
 * D: 迷宫的门
 * . : 空的方格
 */
class MazeTag{

    Integer m,n;
    String tag;

    public MazeTag(Integer m, Integer n, String tag) {
        this.m = m;
        this.n = n;
        this.tag = tag;
    }
}
