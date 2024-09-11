package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ans2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        //계단 정보
        Integer[] stairs = new Integer[n+1];
        stairs[0] = 0;
        for (int i = 1; i < n+1; i++) {
            stairs[i] = Integer.parseInt(bf.readLine());
        }

        //최댓값 정보
        Integer[] dp = new Integer[n+1];
        dp[0] = 0;
        dp[1] = stairs[1];

        if (n >= 2) {
            dp[2] = stairs[1] + stairs[2];
        }

        System.out.println(topDown(dp, stairs, n));
    }

    public static Integer topDown(Integer[] dp, Integer[] stairs, int n) {
        if (dp[n] == null) {
            dp[n] = Math.max(topDown(dp, stairs, n-2), topDown(dp, stairs, n-3) + stairs[n-1]) + stairs[n];
        }

        return dp[n];
    }

    public static Integer bottomUp(Integer[] dp, Integer[] stairs, int n) {
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i-2], dp[i-3] + stairs[i-1]) + stairs[n];
        }

        return dp[n];
    }

}
