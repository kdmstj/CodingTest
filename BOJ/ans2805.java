package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ans2805 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[] arr = new int[a];
        int start = 0;
        int end = -1;
        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < a; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            end = Math.max(arr[i], end);
        }


        long answer = getUpperBound(start, end, arr, b);
        System.out.println(answer - 1);
    }

    private static long getUpperBound(int start, int end, int[] arr, long b){
        while (start < end) {
            int mid = (start + end) / 2;

            long sum = 0;
            for(int i = 0; i < arr.length; i++){
                if (arr[i] - mid > 0) {
                    sum += (arr[i] - mid);
                }
            }

            if(sum < b){
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }
}
