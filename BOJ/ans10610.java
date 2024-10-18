package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ans10610 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String N = bf.readLine();
        char[] arr = N.toCharArray();

        Arrays.sort(arr);

        int sum = 0;
        for(int i =0 ; i < arr.length; i++){
            sum += arr[i] - '0';
        }

        if(sum % 3 != 0 || arr[0] != '0'){
            System.out.println(-1);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = arr.length-1; i>=0 ; i--) {
            sb.append(arr[i]);
        }

        System.out.println(sb.toString());
    }
}
