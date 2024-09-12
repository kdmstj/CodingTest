package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ans11726 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        Integer[] dp = new Integer[n+2];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        //n은 가로의 길이를 나타낸다.
        System.out.println(recursion(dp, n));
        System.out.println(bottomUp(dp, n));
    }

    private static int recursion(Integer[] dp, int n) {
        if (dp[n] == null) {
            dp[n] = (recursion(dp, n-1) + recursion(dp, n-2)) % 10007;
        }

        return dp[n];
    }

    private static int bottomUp(Integer[] dp, int n) {
        for (int i = 3; i < n + 1; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 10007;
        }

        return dp[n];
    }
}
