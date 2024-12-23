package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ans14889 {
    static int[][] arr;
    static boolean[] visited;
    static int N;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited[0] = true;
        dfs(0, 1);

        System.out.println(answer);
    }

    static void dfs(int index, int depth) {
        if (depth >= N / 2) {
            int diff = getDiff();
            answer = Math.min(diff, answer);
            return;
        }

        for (int i = index + 1; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i, depth + 1);
                visited[i] = false;
            }
        }
    }

    static int getDiff() {

        int startS = 0;
        int linkS = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i+ 1; j < N; j++) {
                if (visited[i] && visited[j]) {
                    startS += arr[i][j] + arr[j][i];
                } else if(!visited[i] && !visited[j]) {
                    linkS += arr[i][j] + arr[j][i];
                }
            }
        }

        return Math.abs(startS - linkS);
    }
}
