package BOJ.samsung;

import java.util.*;
import java.io.*;

public class ans16926 {

    public static int N;
    public static int M;
    public static int R;
    public static long[][] map;
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new long[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int layerCount = Math.min(N, M) / 2;

        for (int j = 0; j < layerCount; j++) {
            int row = N - j * 2;
            int col = M - j * 2;
            int perimeter = 2 * (row + col) - 4;
            int rotateCount = R % perimeter;

            for (int r = 0; r < rotateCount; r++) {
                long temp = map[j][j];

                // left
                for (int k = j; k < M - 1 - j; k++) map[j][k] = map[j][k + 1];
                // up
                for (int k = j; k < N - 1 - j; k++) map[k][M - 1 - j] = map[k + 1][M - 1 - j];
                // right
                for (int k = M - 1 - j; k > j; k--) map[N - 1 - j][k] = map[N - 1 - j][k - 1];
                // down
                for (int k = N - 1 - j; k > j + 1; k--) map[k][j] = map[k - 1][j];
                map[j + 1][j] = temp;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
