package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ans1743_dfs {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < k; i++) {
            StringTokenizer st2 = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st2.nextToken()) - 1;
            int y = Integer.parseInt(st2.nextToken()) - 1;
            map[x][y] = 1;
        }

        int answer = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(!visited[i][j] && map[i][j] == 1){
                    count = 0;
                    dfs(i, j, visited, map);
                    answer = Math.max(count, answer);
                }
            }
        }

        System.out.println(answer);
    }

    private static void dfs(int x, int y, boolean[][] visited, int[][] map){
        visited[x][y] = true;
        count++;

        for(int i = 0; i < 4; i++){
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if(checkRange(nextX, nextY, map.length, map[0].length) && !visited[nextX][nextY] && map[nextX][nextY] == 1){
                dfs(nextX, nextY, visited, map);
            }
        }
    }

    private static boolean checkRange(int x, int y, int n, int m){
        return x >= 0 && y >= 0 && x < n && x < m;
    }


}
