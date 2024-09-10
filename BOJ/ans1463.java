package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ans1463 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());

        bottomUp(n);
        topdown(n);
    }

    private static void bottomUp(int n) {
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 0;

        for (int i = 2; i < dp.length; i++) {
            if (i % 6 == 0) {
                dp[i] = Math.min(dp[i/3], dp[i/2]) + 1;
            } else if (i % 2 == 0) {
                dp[i] = Math.min(dp[i/2], dp[i-1]) + 1;
            } else if (i % 3 == 0) {
                dp[i] = Math.min(dp[i / 3], dp[i - 1]) + 1;
            } else {
                dp[i] = dp[i-1] + 1;
            }
        }

        System.out.println(dp[n]);
    }

    private static void topdown(int n) {
        int[] dp = new int[n +1];
        for (int i = 2; i < dp.length; i++) {
            dp[i] = -1;
        }

        dp[0] = dp[1] = 0;

        System.out.println(getDp(dp, n));
    }

    private static int getDp(int[] dp, int n) {
        if (dp[n] == -1) {
            if (n % 6 == 0) {
                dp[n] = Math.min(getDp(dp, n/3), getDp(dp, n/2)) + 1;
            } else if (n % 2 == 0) {
                dp[n] = Math.min(getDp(dp, n/2), getDp(dp, n-1)) + 1;
            } else if (n % 3 == 0) {
                dp[n] = Math.min(getDp(dp, n / 3), getDp(dp, n - 1)) + 1;
            } else {
                dp[n] = getDp(dp, n-1) + 1;
            }
        }

        return dp[n];
    }
}
