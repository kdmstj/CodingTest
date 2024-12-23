package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ans14888 {

    static long[] arr;
    static char[] opArr;
    static boolean[] visited;
    static long min = Integer.MAX_VALUE;
    static long max = Integer.MIN_VALUE;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new long[N];
        opArr = new char[N - 1];
        visited = new boolean[N - 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int index = 0;
        for (int i = 0; i < 4; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(num == 0) continue;
            for (int j = 0; j < num; j++) {
                switch (i) {
                    case 0:
                        opArr[index] = '+';
                        break;
                    case 1:
                        opArr[index] = '-';
                        break;
                    case 2:
                        opArr[index] = '*';
                        break;
                    case 3:
                        opArr[index] = '%';
                        break;
                }
                index++;
            }
        }

        for (int i = 0; i < N - 1; i++) {
            long result = calculate(arr[0], arr[1], opArr[i]);
            visited[i] = true;
            dfs(result, 1);
            visited[i] = false;
        }

        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(long result, int depth) {
        if (depth >= N - 1) {
            min = Math.min(result, min);
            max = Math.max(result, max);
            return;
        }

        for (int i = 0; i < N - 1; i++) {
            if (!visited[i]) {
                long result2 = calculate(result, arr[depth + 1], opArr[i]);
                visited[i] = true;
                dfs(result2, depth + 1);
                visited[i] = false;
            }
        }
    }

    static long calculate(long a, long b, char c) {
        long result = 0L;
        switch (c) {
            case '+':
                result = a + b;
                break;
            case '-':
                 result = a - b;
                 break;
            case '*':
                result = a * b;
                break;
            case '%':
                if (a < 0) {
                    result =  -(Math.abs(a) / b);
                } else {
                    result =  a / b;
                }
                break;
        }

        return result;
    }
}
