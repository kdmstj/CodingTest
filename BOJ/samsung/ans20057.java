package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ans20057 {

    static int N;
    static int[][] arr;
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int answer = 0;
    static int x, y, d = 0;
    static int length = 0;
    static int remain;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        x = N / 2; y = N / 2;
        while (!(isOutOfBound(x, y))) {
            if (d % 4 == 0) {
                length++;
            }

            for (int i = 0; i < length; i++) {
                //새로 갱신된 것이 y 노드
                x += dx[d];
                y += dy[d];

                if(isOutOfBound(x, y)) {
                    break;
                }

                int sand = arr[x][y];
                remain = sand;
                arr[x][y] = 0;

                // 모래가 없다면 continue
                if (sand == 0) continue;

                //1. y의 위아래로 각각 7%, 2%
                moveUpDown(sand, 2);
                moveUpDown(sand, 6);

                //2. y의 왼쪽 대각산은 각각 10%, y의 오른쪽 대각선은 각각 1%, y의 왼쪽으로 두칸은 5%, y의 왼쪽 1칸은 남은 모래의 양
                leftCross(sand, 1);
                leftCross(sand, 7);
                rightCross(sand, 3);
                rightCross(sand, 5);


                //3. 왼쪽으로 두칸 5%
                left(sand);

                //4. 나머지
                int alphaX = x + dx[d];
                int alphaY = y + dy[d];
                if (isOutOfBound(alphaX, alphaY)) {
                    answer += remain;
                } else {
                    arr[alphaX][alphaY] += remain;
                }

                System.out.println("---남아 있는 모래 근황---");
                for(int k = 0; k < N; k++){
                    System.out.println(Arrays.toString(arr[k]));
                }
            }

            //토네이도 방향 전환
            d = (d + 2) % 8;
        }

        System.out.println(answer);
    }

    private static void moveUpDown(int sand, int k) {
        int spread1 = sand * 7 / 100;
        int spread2 = sand * 2 / 100;
        remain -= spread2;
        remain -= spread1;

        int nextX = x + dx[(d + k) % 8];
        int nextY = y + dy[(d + k) % 8];
        if (isOutOfBound(nextX, nextY)) {
            answer += spread1;
        } else {
            arr[nextX][nextY] += spread1;
        }

        nextX += dx[(d + k) % 8];
        nextY += dy[(d + k) % 8];
        if (isOutOfBound(nextX, nextY)) {
            answer += spread2;
        } else {
            arr[nextX][nextY] += spread2;
        }
    }

    private static void leftCross(int sand, int k) {
        int spread = sand * 10 / 100;
        remain -= spread;


        int leftCrossX = x + dx[(d + k) % 8];
        int leftCrossY = y + dy[(d + k) % 8];
        if (isOutOfBound(leftCrossX, leftCrossY)) {
            answer += spread;
        } else {
            arr[leftCrossX][leftCrossY] += spread;
        }
    }

    private static void rightCross(int sand, int k) {
        int spread = sand / 100;
        remain -= spread;

        int rightCrossX = x + dx[(d + k) % 8];
        int rightCrossY = y + dy[(d + k) % 8];
        if (isOutOfBound(rightCrossX, rightCrossY)) {
            answer += spread;
        } else {
            arr[rightCrossX][rightCrossY] += spread;
        }
    }

    private static void left(int sand) {
        int spread = sand * 5 / 100;
        remain -= spread;

        int leftX = x + dx[d] * 2;
        int leftY = y + dy[d] * 2;
        if (isOutOfBound(leftX, leftY)) {
            answer += spread;
        } else {
            arr[leftX][leftY] += spread;
        }
    }


    private static boolean isOutOfBound(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= N;
    }
}
