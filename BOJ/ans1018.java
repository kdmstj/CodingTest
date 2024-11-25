package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ans1018 {
    static char[][] board;
    static int answer = Integer.MAX_VALUE;
    static char[] color = {'B', 'W'};
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        for (int start_x = 0; N - start_x >= 8; start_x++) {
            for (int start_y = 0; M - start_y >= 8; start_y++) {
                check(start_x, start_y);
            }
        }

        System.out.println(answer);
    }

    static void check(int startX, int startY) {
        //B로 시작
        count = getCount(startX, startY,1);
        answer = Math.min(count, answer);

        //W로 시작
        count = getCount(startX, startY,0);
        answer = Math.min(count, answer);
    }

    private static int getCount(int startX, int startY, int color_index) {
        int count = 0;
        for(int i = startX; i < startX + 8; i++){
            color_index++;
            for (int j = startY; j < startY + 8; j++) {
                if (color[color_index % 2] != board[i][j]) {
                    count++;
                }
                color_index++;
            }
        }
        return count;
    }
}
