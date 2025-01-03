package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ans16235 {

    static int N;
    static int M;
    static int K;
    static int[][] ground;
    static int[][] A;
    static List<Integer>[][] arr;
    static int[] dx = {-1, -1, -1, 0, 0, +1, +1, +1};
    static int[] dy = {-1, 0, +1, -1, +1, -1, 0, +1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ground = new int[N][N];
        A = new int[N][N];
        arr = new ArrayList[N][N];

        //겨울에 주어야 하는 양분, 땅에 있는 양분 5로 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                ground[i][j] = 5;
                arr[i][j] = new ArrayList<>();
            }
        }

        //현재 심어져 있는 나무의 나이
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            arr[x - 1][y - 1].add(age);
        }

        for (int year = 0; year < K; year++) {
            springAndSummer();

            fall();

            winter();
        }

        //K 년이 지난 후 살아있는 나무의 개수
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += arr[i][j].size();
            }
        }

        System.out.println(answer);
    }

    static void springAndSummer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                List<Integer> list = arr[i][j]; //현재 노드에 해당하는 나무들의 나이들
                if (list.isEmpty()) continue;
                Collections.sort(list); //오름차순으로 정렬

                //spring
                List<Integer> new_list = new ArrayList<>();
                for (int k = 0; k < list.size(); k++) {
                    int age = list.get(k);
                    if (ground[i][j] - age >= 0) {
                        new_list.add(age + 1);
                        ground[i][j] -= age;
                    } else {
                        //summer
                        for (int l = k; l < list.size(); l++) {
                            ground[i][j] += list.get(l) / 2;
                        }
                        break;
                    }
                }

                arr[i][j] = new_list;
            }
        }
    }

    static void fall() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                List<Integer> list = arr[i][j];
                for (int tree : list) {
                    if (tree % 5 == 0) {
                        addTree(i, j);
                    }
                }
            }
        }

    }

    static void addTree(int x, int y) {
        for (int i = 0; i < 8; i++) {
            int tempX = x + dx[i];
            int tempY = y + dy[i];
            if (tempX >= 0 && tempX < N && tempY >= 0 && tempY < N) {
                arr[tempX][tempY].add(1);
            }
        }
    }

    static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ground[i][j] += A[i][j];
            }
        }
    }
}

