package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ans10448 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int N = Integer.parseInt(br.readLine());

            boolean isAvailable = false;
            loop1:
            for (int a = 1; a < N; a++) {
                for (int b = 1; b <= a; b++) {
                    for (int c = 1; c <= b; c++) {
                        int num = a * (a + 1) / 2 + b * (b + 1) / 2 + c * (c + 1) / 2;
                        if (num == N) {
                            isAvailable = true;
                            break loop1;
                        }
                    }
                }
            }
            System.out.println(isAvailable ? 1 : 0);
        }
    }
}
