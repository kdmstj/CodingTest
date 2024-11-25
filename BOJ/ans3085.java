package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ans3085 {

    static char[][] arr;
    static int answer;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new char[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char current = arr[i][j];
                //오른쪽과 변경 가능한지 확인
                if (j + 1 < N && arr[i][j + 1] != current) {
                    char[][] temp1 = new char[N][N];
                    for (int k = 0; k < N; k++) {
                        temp1[k] = arr[k].clone();
                    }
                    temp1[i][j] = temp1[i][j + 1];
                    temp1[i][j + 1] = current;
                    check(temp1);
                }

                //아래쪽과 변경 가능한지 확인
                if (i + 1 < N && arr[i + 1][j] != current) {
                    char[][] temp2 = new char[N][N];
                    for (int k = 0; k < N; k++) {
                        temp2[k] = arr[k].clone();
                    }
                    temp2[i][j] = temp2[i + 1][j];
                    temp2[i + 1][j] = current;
                    check(temp2);
                }
            }
        }

        System.out.println(answer);
    }

    static void check(char[][] temp) {
        for (int i = 0; i < N; i++) {
            int count = 1;
            for(int j = 0; j < N-1; j++){
                if(temp[i][j] == temp[i][j+1]){
                    count++;
                    answer = Math.max(count, answer);
                } else {
                    count = 1;
                }
            }
        }

        for (int j = 0; j < N; j++) {
            int count = 1;
            for (int i = 0; i < N - 1; i++) {
                if(temp[i][j] == temp[i+1][j]){
                    count++;
                    answer = Math.max(count, answer);
                } else {
                    count = 1;
                }
            }
        }
    }
}
