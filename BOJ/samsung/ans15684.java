package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ans15684 {

    static int N;
    static int M;
    static int H;
    static int[][] arr;
    static int answer = 4;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        arr = new int[H + 1][N + 1]; //1이면 오른쪽으로, 2면 왼쪽으로 이동한다.

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            arr[h][n] = 1;
            arr[h][n + 1] = 2;
        }

        dfs(1, 0); // h : x 좌표, count : 사다리 갯수
        System.out.println(answer != 4 ? answer : -1);
    }

    private static void dfs(int h, int count) {
        if (count >= answer) {
            return;
        } else {
            if (check()) {
                answer = count;
                return;
            }
        }

        for (int i = h; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (arr[i][j] == 0 && arr[i][j + 1] == 0) {
                    arr[i][j] = 1;
                    arr[i][j + 1] = 2;
                    dfs(i, count + 1);
                    arr[i][j] = arr[i][j + 1] = 0;
                }
            }
        }
    }


    private static boolean check() {
        for (int i = 1; i <= N; i++) {
            int y = i;
            for (int j = 1; j <= H; j++) {
                if (arr[j][y] == 1) {
                    y++;
                } else if (arr[j][y] == 2) {
                    y--;
                }
            }
            if (y != i) {
                return false;
            }
        }
        return true;
    }
}
