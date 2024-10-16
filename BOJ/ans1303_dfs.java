package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ans1303_dfs {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Character[][] charArr = new Character[m][n];

        for(int i = 0; i < m ; i++){
            String line = bf.readLine();
            for(int j = 0; j < n; j++){
                charArr[i][j] = line.charAt(j);
            }
        }

        int wCount = 0;
        int bCount = 0;
        boolean[][] visited = new boolean[m][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(visited[i][j]){
                    continue;
                }

                count = 0;
                if(charArr[i][j] == 'W'){
                    dfs(charArr, visited, 'W', i, j);
                    System.out.println("i : " + i);
                    System.out.println("j : " + j);
                    System.out.println("count : " + count);
                    wCount += count * count;
                }

                if(charArr[i][j] == 'B'){
                    dfs(charArr, visited, 'B', i, j);
                    bCount += count * count;
                }
            }
        }

        System.out.println(wCount + " " + bCount);
    }

    private static void dfs(Character[][] charArr, boolean[][] visited, char c, int x, int y){
        visited[x][y] = true;
        count++;

        for(int i = 0; i < 4; i++){
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if(rangeCheck(nextX, nextY, charArr) && charArr[nextX][nextY] == c && !visited[nextX][nextY]){
                dfs(charArr, visited, c, nextX, nextY);
            }
        }
    }

    private static boolean rangeCheck(int x, int y, Character[][] charArr) {
        return x >= 0 && y >= 0 && x < charArr.length && y < charArr[0].length;
    }
}
