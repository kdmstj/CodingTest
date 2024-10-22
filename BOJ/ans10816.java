package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ans10816 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m ; i++){
            int num = Integer.parseInt(st.nextToken());
            int lowerBound = lowerBound(num, arr);
            int upperBound = upperBound(num, arr);
            sb.append((upperBound - lowerBound)).append(" ");
        }

        System.out.println(sb.toString());
    }

    private static int lowerBound(int num, int[] arr) {
        int startIdx = 0;
        int endIdx = arr.length;

        while (startIdx < endIdx) {
            int midIdx = (startIdx + endIdx) / 2;

            if(num <= arr[midIdx]){
                endIdx = midIdx;
            } else {
                startIdx = midIdx + 1;
            }
        }

        return startIdx;
    }

    private static int upperBound(int num, int[] arr) {
        int startIdx = 0;
        int endIdx = arr.length;

        while (startIdx < endIdx) {
            int midIdx = (startIdx + endIdx) / 2;

            if(num < arr[midIdx]){
                endIdx = midIdx;
            } else {
                startIdx = midIdx + 1;
            }
        }

        return startIdx;
    }
}
