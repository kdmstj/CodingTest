package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ans14890 {

    static int N;
    static int L;
    static int[][] arr;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        calRow();
        calCol();


        System.out.println(answer);
    }

    private static void calRow() {
        for (int row = 0; row < N; row++) {
            int idx1 = 0;
            int idx2 = 1;
            boolean[] installed = new boolean[N];
            boolean isRoad = true;
            loop1:
            while (idx2 < N) {
                int element1 = arr[row][idx1];
                int element2 = arr[row][idx2];

                if (element1 == element2) {
                    idx1++;
                    idx2++;
                    continue;
                }

                if (Math.abs(element1 - element2) > 1) {
                    isRoad = false;
                    break loop1;
                }

                //오름차순
                if (element1 + 1 == element2) {
                    int count = 0;
                    int tempIdx = idx1;
                    while (count != L) {
                        if (tempIdx < 0) {
                            isRoad = false;
                            break loop1;
                        }

                        if (arr[row][tempIdx] == element1 && !installed[tempIdx]) {
                            count++;
                            installed[tempIdx] = true;
                            tempIdx--;
                        } else {
                            isRoad = false;
                            break loop1;
                        }
                    }

                    idx1++;
                    idx2++;
                }

                //내림차순
                if (element1 == element2 + 1) {
                    int count = 0;
                    int tempIdx = idx2;
                    while (count != L) {
                        if (tempIdx >= N) {
                            isRoad = false;
                            break loop1;
                        }

                        if (arr[row][tempIdx] == element2 && !installed[tempIdx]) {
                            count++;
                            installed[tempIdx] = true;
                            tempIdx++;
                        } else {
                            isRoad = false;
                            break loop1;
                        }
                    }
                    idx1 = tempIdx - 1;
                    idx2 = idx1 + 1;
                }
            }
            if (isRoad) {
                answer++;
            }
        }
    }

    private static void calCol() {
        for (int col = 0; col < N; col++) {
            int idx1 = 0;
            int idx2 = 1;
            boolean[] installed = new boolean[N];
            boolean isRoad = true;
            loop2:
            while (idx2 < N) {
                int element1 = arr[idx1][col];
                int element2 = arr[idx2][col];

                if (element1 == element2) {
                    idx1++;
                    idx2++;
                    continue;
                }

                if (Math.abs(element1 - element2) > 1) {
                    isRoad = false;
                    break loop2;
                }

                //오름차순
                if (element1 + 1 == element2) {
                    int count = 0;
                    int tempIdx = idx1;
                    while (count != L) {
                        if (tempIdx < 0) {
                            isRoad = false;
                            break loop2;
                        }

                        if (arr[tempIdx][col] == element1 && !installed[tempIdx]) {
                            count++;
                            installed[tempIdx] = true;
                            tempIdx--;
                        } else {
                            isRoad = false;
                            break loop2;
                        }
                    }

                    idx1++;
                    idx2++;
                }

                //내림차순
                if (element1 == element2 + 1) {
                    int count = 0;
                    int tempIdx = idx2;
                    while (count != L) {
                        if (tempIdx >= N) {
                            isRoad = false;
                            break loop2;
                        }

                        if (arr[tempIdx][col] == element2 && !installed[tempIdx]) {
                            count++;
                            installed[tempIdx] = true;
                            tempIdx++;
                        } else {
                            isRoad = false;
                            break loop2;
                        }
                    }
                    idx1 = tempIdx - 1;
                    idx2 = idx1 + 1;
                }
            }
            if (isRoad) {
                answer++;
            }
        }
    }
}