package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ans14891 {
    static char[][] map;
    static int[][] dMap;
    static int answer;
    static boolean[] rotated;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[4][8];
        dMap = new int[4][2];
        for (int i = 0; i < 4; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                map[i][j] = s.charAt(j);
            }
            dMap[i][0] = 6;
            dMap[i][1] = 2;
        }

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            rotated = new boolean[4];
            rotate(num - 1, d);
        }

        getScore();

        System.out.println(answer);
    }

    private static void rotate(int index, int direction) {
        int currentLeftIdx = dMap[index][0];
        int currentLeft = map[index][currentLeftIdx];
        int currentRightIdx = dMap[index][1];
        int currentRight = map[index][currentRightIdx];
        rotated[index] = true;

        if (direction == 1) {
            //시계 방향으로 회전
            dMap[index][0] = ((currentLeftIdx + 7) % 8);
            dMap[index][1] = ((currentRightIdx + 7) % 8);
        } else {
            //반시계 방향으로 회전
            dMap[index][0] = ((currentLeftIdx + 1) % 8);
            dMap[index][1] = ((currentRightIdx + 1) % 8);
        }

        if (index - 1 >= 0) {
            //현재 노드 왼쪽 톱니바퀴 회전
            int leftNodeRightIdx = dMap[index - 1][1];
            int leftNodeRight = map[index-1][leftNodeRightIdx];
            if (currentLeft != leftNodeRight && !rotated[index - 1]) {
                rotate(index - 1, direction * (-1));
            }
        }

        if (index + 1 < 4) {
            //현재 노드 오른쪽 톱니바퀴 회전
            int rightNodeLeftIdx = dMap[index + 1][0];
            int rightNodeLeft = map[index+1][rightNodeLeftIdx];
            if (currentRight != rightNodeLeft && !rotated[index + 1]) {
                rotate(index + 1, direction * (-1));
            }
        }


    }

    private static void getScore() {
        for (int i = 0; i < 4; i++) {
            int index = (dMap[i][0] + 2) % 8;

            if (map[i][index] == '1') {
                answer += Math.pow(2, i);
            }
        }
    }
}
