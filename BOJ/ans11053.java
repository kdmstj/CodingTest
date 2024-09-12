package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ans11053 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        int[] arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N+1];
        dp[0] = 1;
//        bottomUp(N, arr, dp);
        topDown(N-1, arr, dp);
        Arrays.sort(dp);
        System.out.println(Arrays.toString(dp));
        System.out.println(dp[N]);
    }

    private static void bottomUp(int N, int[] arr, int[] dp) {
        for (int i = 1; i < N; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
        }
    }

    private static int topDown(int n, int[] arr, int[] dp) {
        if (n == 0) return 1;
        if (dp[n] > 0) return dp[n];

        int max = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] < arr[n]) {
                max = Math.max(max, topDown(i, arr, dp));
            }
        }
        dp[n] = max + 1;
        System.out.println("n : " + n + "dp : " + dp[n]);

        return dp[n];
    }
}
