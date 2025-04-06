
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        Generator maze = new Generator(10, 10);
        maze.printMatrix();
        List<int[]> path = maze.getPath();

        List<int[]> shortestPath = maze.getShortestPath();
        printPath(shortestPath);

    }

    static void printPath(List<int[]> path) {

        for (int[] i : path) {
            System.out.println(i[0] + "  " + i[1]);
        }

    }

}
