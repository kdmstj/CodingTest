package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ans1890 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int[][] arr = new int[N][N];
        for(int i = 0; i < N ; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < N ; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] dp = new long[N][N];
        dp[0][0] = 1;

        for(int i = 0; i < N ; i++){
            for(int j = 0; j < N ; j++){
                if(i == N-1 && j == N-1){
                    break;
                }

                if(j + arr[i][j] < N){
                    dp[i][j + arr[i][j]] += dp[i][j];
                }

                if(i + arr[i][j] < N){
                    dp[i+arr[i][j]][j] += dp[i][j];
                }
            }
        }
        System.out.println(dp[N-1][N-1]);
    }
}
