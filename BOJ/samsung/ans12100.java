package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ans12100 {

    static int answer = 0;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //dfs 로 최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값을 구해야 한다.
        dfs(arr, 0);
        System.out.println(answer);
    }

    static void dfs(int[][] arr, int depth) {
        if (depth >= 5) {
            findMax(arr);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int[][] move_arr = move(arr, i);
            dfs(move_arr, depth + 1);
        }
    }

    static int[][] move(int[][] arr, int d) {

        int[][] move_arr = new int[N][N];
        if (d == 0) {
            for (int j = 0; j < N; j++) {
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < N; i++) {
                    if (arr[i][j] != 0) {
                        list.add(arr[i][j]);
                    }
                }
                int index = 0;
                int point_index = 0;
                while (point_index < list.size()) {
                    int element1 = list.get(point_index);

                    if (point_index == list.size() - 1) {
                        move_arr[index][j] = element1;
                        break;
                    } else {
                        int element2 = list.get(point_index + 1);
                        if (element1 == element2) {
                            move_arr[index++][j] = element1 + element2;
                            point_index += 2;
                        } else {
                            move_arr[index++][j] = element1;
                            point_index += 1;
                        }
                    }
                }
            }
        } else if (d == 1) {
            for (int j = 0; j < N; j++) {
                List<Integer> list = new ArrayList<>();
                for (int i = N - 1; i >= 0; i--) {
                    if (arr[i][j] != 0) {
                        list.add(arr[i][j]);
                    }
                }
                int index = N - 1;
                int point_index = 0;
                while (point_index < list.size()) {
                    int element1 = list.get(point_index);
                    if (point_index == list.size() - 1) {
                        move_arr[index][j] = element1;
                        break;
                    } else {
                        int element2 = list.get(point_index + 1);

                        if (element1 == element2) {
                            move_arr[index--][j] = element1 + element2;
                            point_index += 2;
                        } else {
                            move_arr[index--][j] = element1;
                            point_index += 1;
                        }
                    }
                }
            }
        } else if (d == 2) {
            for (int i = 0; i < N; i++) {
                List<Integer> list = new ArrayList<>();
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] != 0) {
                        list.add(arr[i][j]);
                    }
                }
                int index = 0;
                int point_index = 0;
                while (point_index < list.size()) {
                    int element1 = list.get(point_index);

                    if (point_index == list.size() - 1) {
                        move_arr[i][index] = element1;
                        break;
                    } else {
                        int element2 = list.get(point_index + 1);

                        if (element1 == element2) {
                            move_arr[i][index++] = element1 + element2;
                            point_index += 2;
                        } else {
                            move_arr[i][index++] = element1;
                            point_index += 1;
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < N; i++) {
                List<Integer> list = new ArrayList<>();
                for (int j = N - 1; j >= 0; j--) {
                    if (arr[i][j] != 0) {
                        list.add(arr[i][j]);
                    }
                }
                int index = N - 1;
                int point_index = 0;
                while (point_index < list.size()) {
                    int element1 = list.get(point_index);
                    if (point_index == list.size() - 1) {
                        move_arr[i][index] = element1;
                        break;
                    } else {
                        int element2 = list.get(point_index + 1);

                        if (element1 == element2) {
                            move_arr[i][index--] = element1 + element2;
                            point_index += 2;
                        } else {
                            move_arr[i][index--] = element1;
                            point_index += 1;
                        }
                    }
                }
            }
        }

        return move_arr;
    }

    static void findMax(int[][] arr) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer = Math.max(answer, arr[i][j]);
            }
        }
    }
}
