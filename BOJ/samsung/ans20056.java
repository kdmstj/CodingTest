package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ans20056 {

    static int N;
    static int M;
    static int K;
    static int answer;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static List<Ball>[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken()); //질량
            int s = Integer.parseInt(st.nextToken()); //속력
            int d = Integer.parseInt(st.nextToken()); //방향
            map[r][c].add(new Ball(m, s, d));
        }




        for (int count = 0; count < K; count++) {
            List<Ball>[][] copyMap = new ArrayList[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    copyMap[i][j] = new ArrayList<>();
                }
            }

            //모든 파이어볼 di 로 si 만큼 이동
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (Ball ball : map[i][j]) {
                        int moveX = (i + (ball.s % N) * dx[ball.d] + N) % N;
                        int moveY = (j + (ball.s % N) * dy[ball.d] + N) % N;
                        copyMap[moveX][moveY].add(new Ball(ball.m, ball.s, ball.d));
                    }
                }
            }

            // 이동이 끝난 후 2개 이상의 파이어볼이 있는 칸 처리
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (copyMap[i][j].size() < 2) continue;

                    int sumM = 0, sumS = 0;
                    boolean isEven = true, isOdd = true;
                    int size = copyMap[i][j].size();

                    for (Ball b : copyMap[i][j]) {
                        sumM += b.m;
                        sumS += b.s;
                        if (b.d % 2 == 0) isOdd = false;
                        else isEven = false;
                    }
                    copyMap[i][j].clear();

                    if (sumM / 5 == 0) continue;
                    boolean sameParity = isEven || isOdd;

                    for (int l = 0; l < 4; l++) {
                        copyMap[i][j].add(new Ball(sumM / 5, sumS / size, sameParity ? l * 2 : l * 2 + 1));
                    }
                }
            }

            // map 업데이트
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = new ArrayList<>(copyMap[i][j]);
                }
            }
        }

        // 최종 질량 합산
        answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (Ball ball : map[i][j]) {
                    answer += ball.m;
                }
            }
        }
        System.out.println(answer);
    }

    public static class Ball {
        int m;
        int s;
        int d;

        public Ball(int m, int s, int d) {
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
}
