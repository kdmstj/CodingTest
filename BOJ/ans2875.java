package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ans2875 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int tCount = 0;
        while (N + M - 3 >= K && (N - 2 >= 0 && M - 1 >= 0)) {
            N -= 2;
            M -= 1;
            tCount++;
        }

        System.out.println(tCount);
    }
}
