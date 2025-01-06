package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ans17144 {

    static int R;
    static int C;
    static int T;
    static int[][] arr;
    static List<Node> v = new ArrayList<>();
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[R][C];

        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == -1) {
                    v.add(new Node(i, j));
                }
            }
        }

        for (int t = 0; t < T; t++) {
            //미세먼지 확산
            int[][] plus = new int[R][C];
            int[][] minus = new int[R][C];

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    int amount = arr[i][j];
                    int count = 0;
                    for (int d = 0; d < 4; d++) {
                        int tempX = i + dx[d];
                        int tempY = j + dy[d];
                        if (isInBounds(tempX, tempY) && arr[tempX][tempY] != -1) {
                            plus[tempX][tempY] += (amount / 5);
                            count++;
                        }
                    }
                    minus[i][j] = -(amount / 5) * count;
                }
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {

                    arr[i][j] +=  plus[i][j] + minus[i][j];
                }
            }

            //공기 청정기 작동
            Node posA = v.get(0); //위쪽
            Node posB = v.get(1); //아래쪽
            int amountA = arr[posA.x][posA.y + 1];
            int amountB = arr[posB.x][posB.y + 1];
            arr[posA.x][posA.y + 1] = 0;
            arr[posB.x][posB.y + 1] = 0;
            blow(posA.x, posA.y+1, amountA, 1, posA);
            blow(posB.x, posB.y+1, amountB, 3, posB);
        }

        int answer = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] > 0) {
                    answer += arr[i][j];
                }
            }
        }

        System.out.println(answer);
    }

    private static void blow(int currentX, int currentY, int amount, int x, Node pos) {
        int d = 0;
        while (true) {
            if (!isInBounds(currentX + dx[d], currentY + dy[d])) {
                d = (d + x) % 4;
            }

            currentX += dx[d];
            currentY += dy[d];

            if (pos.x == currentX && pos.y == currentY) {
                break;
            }

            int tempV = arr[currentX][currentY];
            arr[currentX][currentY] = amount;
            amount = tempV;
        }
    }

    static boolean isInBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < R && y < C;
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
