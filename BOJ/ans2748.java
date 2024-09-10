package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ans2748{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());

//        int[] dp = method1(n);
        int[] dp = method2(n);

        System.out.println(dp[n]);
    }

    private static int[] method1(int n) {
        int[] dp = new int[n +1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp;
    }

    private static int[] method2(int n) {
        int[] dp = new int[n+1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }

        dp[0] = 0;
        dp[1] = 1;

        Fib(dp, n);

        return dp;
    }

    private static int Fib(int[] dp, int N) {
        if (dp[N] == -1) {
            dp[N] = Fib(dp, N-1) + Fib(dp, N-2);
        }

        return dp[N];
    }
}