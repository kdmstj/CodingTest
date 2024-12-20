package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ans14501 {

    static int N;
    static int[][] arr;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1][2];
        for (int day = 1; day <= N; day++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[day][0] = Integer.parseInt(st.nextToken()); //소요 시간
            arr[day][1] = Integer.parseInt(st.nextToken()); //비용
        }

        dfs(0, 0);
        for (int i = 1; i <= N; i++) {
            if (i + arr[i][0] <= N + 1) {
                dfs(i + arr[i][0], arr[i][1]);
            }
        }
        System.out.println(answer);
    }

    static void dfs(int startDay, int paySum) {
        System.out.println("startDay : " + startDay + " paySum : " + paySum);
        for (int i = startDay; i <= N; i++) {
            int t = arr[i][0]; //해당 날짜에 소요되는 시간
            int p = arr[i][1]; //해당 날짜 일당
            if (i + t <= N + 1) {
                dfs(i + t, paySum + p);
            }
        }

        answer = Math.max(paySum, answer);
    }
}
