package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ans14500 {

    static int max = Integer.MIN_VALUE;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;
    static int[][] arr;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, arr[i][j], 1);
                visited[i][j] = false;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int d = 0; d < 2; d++) {
                    visited[i][j] = true;
                    dfs2(i, j, arr[i][j], 1, d);
                    visited[i][j] = false;
                }
            }
        }

        System.out.println(max);
    }

    static void dfs(int x, int y, int sum, int depth) {
        if (depth >= 4) {
            max = Math.max(sum, max);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int moveX = x + dx[i];
            int moveY = y + dy[i];

            if (isNotValidToGo(moveX, moveY)) {
                continue;
            }

            visited[moveX][moveY] = true;
            dfs(moveX, moveY, sum + arr[moveX][moveY], depth + 1);
            visited[moveX][moveY] = false;
        }
    }

    static void dfs2(int x, int y, int sum, int depth, int d) {
        if (depth >= 4) {
            max = Math.max(max, sum);
            return;
        }

        int moveX = x + dx[d];
        int moveY = y + dy[d];

        if (isNotValidToGo(moveX, moveY)) {
            return;
        }

        if (depth == 1) {
            if (d == 0) {
                //아래
                if (!isNotValidToGo(moveX + 1, moveY)) {
                    visited[moveX][moveY] = true;
                    visited[moveX + 1][moveY] = true;
                    dfs(moveX, moveY, sum + arr[moveX][moveY] + arr[moveX + 1][moveY], depth + 2);
                    visited[moveX][moveY] = false;
                    visited[moveX + 1][moveY] = false;
                }

                if (!isNotValidToGo(moveX - 1, moveY)) {
                    visited[moveX][moveY] = true;
                    visited[moveX - 1][moveY] = true;
                    dfs(moveX, moveY, sum + arr[moveX][moveY] + arr[moveX - 1][moveY], depth + 2);
                    visited[moveX][moveY] = false;
                    visited[moveX - 1][moveY] = false;
                }
            } else {

                if (!isNotValidToGo(moveX, moveY - 1)) {
                    visited[moveX][moveY] = true;
                    visited[moveX][moveY - 1] = true;
                    dfs(moveX, moveY, sum + arr[moveX][moveY] + arr[moveX][moveY - 1], depth + 2);
                    visited[moveX][moveY] = false;
                    visited[moveX][moveY - 1] = false;
                }

                if (!isNotValidToGo(moveX, moveY + 1)) {
                    visited[moveX][moveY] = true;
                    visited[moveX][moveY + 1] = true;
                    dfs(moveX, moveY, sum + arr[moveX][moveY] + arr[moveX][moveY + 1], depth + 2);
                    visited[moveX][moveY] = false;
                    visited[moveX][moveY + 1] = false;
                }
            }
        } else {
            visited[moveX][moveY] = true;
            dfs(moveX, moveY, sum + arr[moveX][moveY], depth + 1);
            visited[moveX][moveY] = false;
        }
    }

    private static boolean isNotValidToGo(int moveX, int moveY) {
        return moveX < 0 || moveY < 0 || moveX >= N || moveY >= M || visited[moveX][moveY];
    }
}
