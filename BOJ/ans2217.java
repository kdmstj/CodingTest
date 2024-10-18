package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ans2217 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(bf.readLine());
        int[] arr = new int[k];

        for(int i = 0; i < k ; i++){
            arr[i] = Integer.parseInt(bf.readLine());
        }

        Arrays.sort(arr);

        int weightMin = arr[0];
        int weighSumMax = k * arr[0];
        for(int i = 0 ; i < k ; i++){
            if(arr[i] <= weightMin){
                continue;
            }

            weightMin = arr[i];
            weighSumMax = Math.max(weighSumMax, (k-i)*arr[i]);
        }

        System.out.println(weighSumMax);
    }
}
