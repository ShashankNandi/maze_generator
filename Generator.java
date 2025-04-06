
import java.util.*;

public class Generator {

    char[][] maze;
    int n;
    int m;
    Random random;

    public Generator(int n, int m) {
        this.n = n;
        this.m = m;
        random = new Random();
        maze = new char[n][m];
        generateRandomPath(n, m);
    }

    char[][] getMaze() {
        return maze;
    }

    public void generateRandomPath(int n, int m) {
        // char[][] maze = new char[n][m];
        Random random = new Random();
        for (char[] i : maze) {
            Arrays.fill(i, '#');
        }
        maze[0][0] = '.';

        List<int[]> path = new ArrayList<>();

        dfs(0, 0, path);

        randomize(path, random);

    }

    void randomize(List<int[]> path, Random random) {

        int n = maze.length;
        int m = maze[0].length;
        boolean[][] visited = new boolean[n][m];

        char[] ch = {'.', '#'};

        for (int[] i : path) {
            int r = i[0];
            int c = i[1];
            visited[r][c] = true;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    char c = ch[random.nextInt(2)];
                    maze[i][j] = c;
                }
            }
        }

    }

    boolean dfs(int r, int c, List<int[]> path) {

        int n = maze.length;
        int m = maze[0].length;
        if (r == n - 1 && c == m - 1) {
            path.add(new int[]{r, c});
            maze[r][c] = '.';
            return true;
        }

        char node = maze[r][c];

        int[] rDir = {-1, 0, 1, 0};
        int[] cDir = {0, 1, 0, -1,};

        List<int[]> possibleDir = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            if (r + rDir[i] < n && r + rDir[i] >= 0 && c + cDir[i] >= 0 && c + cDir[i] < m && maze[r + rDir[i]][c + cDir[i]] != '.') {
                possibleDir.add(new int[]{r + rDir[i], c + cDir[i]});
            }
        }

        // path not possible
        int len = possibleDir.size();
        // if(len==0){
        //     return false;
        // }

        Collections.shuffle(possibleDir);

        maze[r][c] = '.';
        for (int i = 0; i < len; i++) {

            int[] randomPossibleDir = possibleDir.get(i);
            boolean found = dfs(randomPossibleDir[0], randomPossibleDir[1], path);
            if (found) {
                path.add(new int[]{r, c});
                return true;
            }
        }

        // maze[r][c]='#';
        return false;

    }

    public void printMatrix() {
        for (char[] row : maze) {
            for (char ch : row) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }

    public List<int[]> getPath() {
        List<int[]> path = new ArrayList<>();
        boolean[][] visited = new boolean[n][m];

        boolean foundPath = dfsPath(path, 0, 0, visited);
        if (foundPath) {
            System.out.println("Path Found");
        } else {
            System.out.println("Path not found");
        }

        Collections.reverse((path));

        return path;
    }

    List<int[]> getShortestPath() {
        List<int[]> shortestPath = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        int[][][] parent = new int[n][m][2];  // store parent coordinates
    
        // Start BFS from (0, 0)
        q.add(new int[]{0, 0});
        visited[0][0] = true;
        parent[0][0] = new int[]{-1, -1}; // starting node has no parent
    
        int[] rDir = {-1, 0, 1, 0};
        int[] cDir = {0, 1, 0, -1};
    
        while (!q.isEmpty()) {
            int[] node = q.poll();
            int r = node[0];
            int c = node[1];
    
            if (r == n - 1 && c == m - 1) {  // Reached end
                // Reconstruct path from end to start
                while (r != -1 && c != -1) {
                    shortestPath.add(new int[]{r, c});
                    int[] p = parent[r][c];
                    r = p[0];
                    c = p[1];
                }
                Collections.reverse(shortestPath);  // to get start -> end order
                return shortestPath;
            }
    
            for (int i = 0; i < 4; i++) {
                int r1 = r + rDir[i];
                int c1 = c + cDir[i];
    
                if (r1 >= 0 && r1 < n && c1 >= 0 && c1 < m && !visited[r1][c1] && maze[r1][c1] == '.') {
                    visited[r1][c1] = true;
                    q.add(new int[]{r1, c1});
                    parent[r1][c1] = new int[]{r, c};  // track parent
                }
            }
        }
    
        return shortestPath;  // empty if no path exists
    }
    

    boolean dfsPath(List<int[]> path, int r, int c, boolean[][] visited) {

        if (r == n - 1 && c == m - 1) {
            path.add(new int[]{r, c});
            return true;
        }

        visited[r][c] = true;

        int[] rDir = {-1, 0, 1, 0};
        int[] cDir = {0, 1, 0, -1,};

        for (int i = 0; i < rDir.length; i++) {
            int r1 = r + rDir[i];
            int c1 = c + cDir[i];
            if (r1 >= 0 && r1 < n && c1 >= 0 && c1 < m && !visited[r1][c1] && maze[r1][c1] == '.') {
                boolean found = dfsPath(path, r1, c1, visited);
                if (found) {
                    path.add(new int[]{r, c});
                    return true;
                }
            }
        }

        visited[r][c] = false;

        return false;
    }
}
