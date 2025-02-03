package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ans17822 {

    static int N;
    static int M;
    static int K;
    static int[][] arr;
    static int answer;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                arr[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            for (int n = 0;  n < N; n++) {
                if(haveToRotate(n, x)){
                    rotate(d, k, n);
                }
            }

            boolean[][] toBeDeleted = new boolean[N][M];
            boolean isValid = false;
            int sum = 0;
            int count = 0;

            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    int current = arr[n][m];
                    if (current == 0) continue;

                    sum += current;
                    count++;

                    //오른쪽 arr[n][m+1]
                    if (arr[n][(m + 1) % M] == current) {
                        toBeDeleted[n][m] = true;
                        toBeDeleted[n][(m + 1) % M] = true;
                        isValid = true;
                    }

                    //바깥쪽 arr[n+1][m]
                    if (n + 1 < N && arr[n + 1][m] == current) {
                        toBeDeleted[n][m] = true;
                        toBeDeleted[n + 1][m] = true;
                        isValid = true;
                    }
                }
            }

            if (!isValid) {
                double average = (double) sum / (double) count;
                for (int n = 0; n < N; n++) {
                    for (int m = 0; m < M; m++) {
                        if (arr[n][m] == 0) continue;
                        if ((double) arr[n][m] > average) {
                            arr[n][m] -= 1;
                        } else if( (double) arr[n][m] < average){
                            arr[n][m] += 1;
                        }
                    }
                }
            } else {
                for (int n = 0; n < N; n++) {
                    for (int m = 0; m < M; m++) {
                        if (toBeDeleted[n][m]) arr[n][m] = 0;
                    }
                }
            }
        }


        calculateSum();
        System.out.println(answer);
    }

    private static boolean haveToRotate(int n, int x) {
        return (n + 1) % x == 0;
    }

    private static void rotate(int d, int k, int n) {
        int[] temp = new int[M];
        if (d == 0) { //시계 방향
            for (int m = 0; m < M; m++) {
                temp[(m + k) % M] = arr[n][m];
            }
        } else { //반시계 방향
            for (int m = 0; m < M; m++) {
                temp[((m - k) + M) % M] = arr[n][m];
            }
        }
        arr[n] = temp;
    }

    private static void calculateSum() {
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                answer += arr[n][m];
            }
        }
    }
}
