package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ans1920 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int m = Integer.parseInt(bf.readLine());
        StringTokenizer st2 = new StringTokenizer(bf.readLine());
        for(int i = 0; i < m ;i++){
            int num = Integer.parseInt(st2.nextToken());
            System.out.println(binarySearch(num, arr));
        }
    }

    private static int binarySearch(int num, int[] arr){
        int startIdx = 0;
        int endIdx = arr.length-1;
        int midIdx = (startIdx + endIdx) / 2;

        while (startIdx <= endIdx) {
            if(arr[midIdx] == num){
                return 1;
            } else if(arr[midIdx] > num){
                endIdx = midIdx - 1;
            } else { //arr[midIdx] < num
                startIdx = midIdx + 1;
            }
            midIdx = (startIdx + endIdx) / 2;
        }

        return 0;
    }
}
