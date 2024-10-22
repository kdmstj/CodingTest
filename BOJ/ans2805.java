package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ans2805 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int a = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        Long[] arr = new Long[a];
        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < a; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        long answer = getUpperBound(arr, b);
        System.out.println(answer - 1);
    }

    private static long getUpperBound(Long[] arr, long b){

        long start = arr[0];
        long end = arr[arr.length-1];

        while (start < end) {
            long mid = (start + end) / 2;

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
