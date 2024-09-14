package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ans11054 {
    static int[] arr;
    static Integer[] dp_i;
    static Integer[] dp_d;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        dp_d = new Integer[n];
        dp_i = new Integer[n];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = increase(i) + decrease(i);
        }

        Arrays.sort(result);
        System.out.println(Arrays.toString(result));
        System.out.println(result[n-1]-1);
    }

    private static int increase(int n) {
        if (dp_i[n] == null) {
            int max = 0;
            for (int i = n - 1; i >= 0; i--) {
                if (arr[i] < arr[n]) {
                    max = Math.max(increase(i), max);
                }
            }
            dp_i[n] = max + 1;
        }

        return dp_i[n];
    }

    private static int decrease(int n) {
        if (dp_d[n] == null) {
            int max = 0;
            for (int i = n+1; i < dp_d.length; i++) {
                if (arr[i] < arr[n]) {
                    max = Math.max(decrease(i), max);
                }
            }
            dp_d[n] = max + 1;
        }

        return dp_d[n];
    }
}
