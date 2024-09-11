package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ans9095 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        Integer[] dp = new Integer[11];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(bf.readLine());
            System.out.println(topDown(dp, num));
            System.out.println(bottomUp(dp, num));
        }
    }

    private static int topDown(Integer[] dp, int num) {
        if (dp[num] == null) {
            dp[num] = topDown(dp, num-1) + topDown(dp, num-2) + topDown(dp, num - 3);
        }

        return dp[num];
    }

    private static int bottomUp(Integer[] dp, int num) {
        for (int i = 4; i < num; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }

        return dp[num];
    }
}
