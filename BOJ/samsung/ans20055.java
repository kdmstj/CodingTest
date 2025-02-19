package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ans20055 {

    static int N;
    static int K;
    static int startIdx;
    static int endIdx;
    static int[] A;
    static boolean[] robot;
    static int answer;
    static int N2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        N2 = N * 2;
        startIdx = 0;
        endIdx = N - 1;
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        A = new int[N2];
        robot = new boolean[N2];
        for (int i = 0; i < N2; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }


        answer = 1;
        while (true) {
            //1. 벨트가 각 칸 위에 있는 로봇과 함께 한칸 이동한다.
            startIdx = (startIdx - 1 + N2) % N2;
            endIdx = (endIdx - 1 + N2) % N2;
            robot[endIdx] = false;

            //2. 가장 먼저 벨트에 올라간 것부터 벨트가 회전하는 방향으로 이동할 수 있다면 이동한다.
            int idx = (endIdx - 1 + N2) % N2;
            while (true) {
                if (robot[idx]) {
                    int nextIdx = (idx + 1) % N2;
                    if (isPossibleToPut(nextIdx)) {
                        put(nextIdx);
                        robot[idx] = false;
                    }
                }

                if (idx == startIdx) break;
                idx = (idx - 1 + N2) % N2;
            }
            robot[endIdx] = false;

            //올리는 위치에 내구도가 0이 아니라면
            if (isPossibleToPut(startIdx)) {
                put(startIdx);
            }


            //내구도가 0개가 몇개인지 확인
            if (countZero() >= K) {
                System.out.println(answer);
                break;
            } else {
                answer++;
            }
        }
    }

    private static void put(int idx) {
        robot[idx] = true;
        A[idx] -= 1;
    }

    private static boolean isPossibleToPut(int idx) {
        return !robot[idx] && A[idx] > 0;
    }

    private static int countZero() {
        int count = 0;
        for (int i = 0; i < N2; i++) {
            if (A[i] == 0) {
                count++;
            }
        }

        return count;
    }
}
