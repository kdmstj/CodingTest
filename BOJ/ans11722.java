package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ans11722 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        dp[0] = 1;
        bottomUp(N, arr, dp);
        Arrays.sort(dp);
        System.out.println(dp[N-1]);
    }

    private static void bottomUp(int N, int[] arr, int[] dp) {
        for (int i = 1; i < N; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]) {
                    max = Math.max(dp[j], max);
                }
            }
            dp[i] = max + 1;
        }
    }

    private static int recursion(int[] arr, int[] dp, int N) {
        int max = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (arr[i] > arr[N]) {
                max = Math.max(recursion(arr, dp, i), max);
            }
        }
        dp[N] = max + 1;

        return dp[N];
    }
}
