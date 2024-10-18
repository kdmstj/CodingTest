package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ans11047 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(bf.readLine());
        }

        int answer = 0;
        for(int i = arr.length-1 ; i >= 0 ; i--){
            if(arr[i] <= k){
                answer += k / arr[i];
                k = k % arr[i];
            }

            if(k == 0){
                break;
            }
        }

        System.out.println(answer);
    }
}
