package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ans17143 {

    static int R;
    static int C;
    static Fish[][] arr;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1}; //상, 좌, 하, 우
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new Fish[R][C];
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());

            if (d == 1) {
                d = 2;
            } else if (d == 2) {
                d = 3;
            } else if (d == 3) {
                d = 1;
            }

            arr[r][c] = new Fish(s, d, z);
        }

        for (int i = 0; i < C; i++) {

            catchFish(i);

            moveFish();
        }

        System.out.println(answer);
    }

    private static void catchFish(int c) {
        for (int i = 0; i < R; i++) {
            if (arr[i][c] != null) {
                answer += arr[i][c].z;
                arr[i][c] = null;
                break;
            }
        }
    }

    private static void moveFish() {
        Fish[][] temp = new Fish[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (arr[r][c] == null) continue;

                Fish fish = arr[r][c];
                int fishSpeed = fish.s;
                if (fish.d == 0 || fish.d == 2) {
                    fishSpeed %= (R - 1) * 2;
                } else {
                    fishSpeed %= (C - 1) * 2;
                }

                int fishX = r;
                int fishY = c;
                for (int t = 0; t < fishSpeed; t++) {
                    if (!inBounds(fishX + dx[fish.d], fishY + dy[fish.d])) {
                        fish.d = (fish.d + 2) % 4;

                    }
                    fishX += dx[fish.d];
                    fishY += dy[fish.d];
                }

                if (temp[fishX][fishY] == null || (temp[fishX][fishY] != null && temp[fishX][fishY].z < fish.z)) {
                    temp[fishX][fishY] = fish;
                }
            }
        }
        arr = temp;
    }

    static boolean inBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < R && y < C;
    }

    static class Fish {
        int s;
        int d;
        int z;

        Fish(int s, int d, int z) {
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

}
