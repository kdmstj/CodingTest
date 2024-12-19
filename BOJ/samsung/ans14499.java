package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ans14499 {

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};//동서북남
    static int N;
    static int M;
    static int[] dice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken()); //현재 x 위치
        int y = Integer.parseInt(st.nextToken()); //현재 y 위치
        int K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st2.nextToken());
            }
        }


        dice = new int[6]; //동, 서, 남, 북, 아래, 위
        dice[0] = 3; dice[1] = 4; dice[2] = 5; dice[3] = 2; dice[4] = 6; dice[5] = 1;

        Map<Integer, Integer> map = new HashMap<>(); //각 주사위 위치별 숫자
        for(int i = 1; i <= 6; i++){
            map.put(i, 0);
        }

        StringTokenizer st3 = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int d = Integer.parseInt(st3.nextToken());
            //지도 이동
            int posX = x + dx[d - 1];
            int posY = y + dy[d - 1];

            if (isOutOfMap(posX, posY)) {
                continue;
            }

            //주사위 이동
            roll(d);

            x = posX;
            y = posY;

            int bottom = dice[4];
            if (arr[posX][posY] == 0) {
                arr[posX][posY] = map.get(bottom); //주사위 아랫면 수
            } else {
                map.put(bottom, arr[posX][posY]);
                arr[posX][posY] = 0;
            }

            //주사위 면 위 숫자
            int top = dice[5];
            System.out.println(map.get(top));
        }
    }

    private static void roll(int d) {
        switch (d) {
            case 1:
                int tmp = dice[0];
                dice[0] = dice[5];
                dice[5] = dice[1];
                dice[1] = dice[4];
                dice[4] = tmp;
                break;
            case 2:
                tmp = dice[0];
                dice[0] = dice[4];
                dice[4] = dice[1];
                dice[1] = dice[5];
                dice[5] = tmp;
                break;
            case 3:
                tmp = dice[2];
                dice[2] = dice[5];
                dice[5] = dice[3];
                dice[3] = dice[4];
                dice[4] = tmp;
                break;
            case 4:
                tmp = dice[2];
                dice[2] = dice[4];
                dice[4] = dice[3];
                dice[3] = dice[5];
                dice[5] = tmp;
        }
    }

    private static boolean isOutOfMap(int posX, int posY) {
        return posX < 0 || posY < 0 || posX >= N || posY >= M;
    }
}
