package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ans1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[] arr = new int[a];
        long left = 0;
        long right = -1;
        for (int i = 0; i < a; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
            right = Math.max(arr[i], right);
        }

        right++;

        while (left < right) {
            long mid = (left + right) / 2;

            long count = 0;
            for (int i = 0; i < arr.length; i++) {
                count += (arr[i] / mid);
            }

            if (count < b) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(right - 1);

    }
}
