package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ans2231 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(func1(N));
    }

    private static int func1(int N) {
        for(int i = 1 ; i < N ; i++){
            int sum = i;

            int m = i;
            while(m > 0){
                sum +=  m % 10;
                m /= 10;
            }

            System.out.println("i : " + i+ " sum : " + sum);
            if(sum == N){
                return i;
            }
        }
        return 0;
    }
}
