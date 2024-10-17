package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ans1743_bfs {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

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
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    int count = 0;

                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;

                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();
                        count++;
                        int currentX = current[0];
                        int currentY = current[1];

                        for (int idx = 0; idx < 4; idx++) {
                            int nextX = currentX + dx[idx];
                            int nextY = currentY + dy[idx];
                            if (checkRange(nextX, nextY, n, m) && !visited[nextX][nextY] && map[nextX][nextY] == 1) {
                                queue.add(new int[]{nextX, nextY});
                                visited[nextX][nextY] = true;
                            }
                        }
                    }
                    answer = Math.max(answer, count);
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean checkRange(int x, int y, int n, int m) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }
}
