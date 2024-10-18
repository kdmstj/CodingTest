package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ans13458 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        StringTokenizer st2 = new StringTokenizer(bf.readLine());
        int a = Integer.parseInt(st2.nextToken());
        int b = Integer.parseInt(st2.nextToken());

        long count = 0;
        for (int i = 0; i < N; i++) {
            arr[i] -= a;
            count++;
            if (arr[i] > 0) {
                count += Math.ceil(arr[i] / (double)b);
            }
        }
        System.out.println(count);
    }
}
