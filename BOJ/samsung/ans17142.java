package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ans17142 {
    static int N;
    static int M;
    static int[][] arr;
    static List<Node> inactive_virus_list = new ArrayList<>();
    static List<Node> active_virus_list = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = Integer.MAX_VALUE;
    static int emptySpace = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 2) {
                    inactive_virus_list.add(new Node(i, j));
                }
                if (arr[i][j] == 0) {
                    emptySpace++;
                }
            }
        }

        if (emptySpace == 0) {
            System.out.println(0);
        } else {
            dfs(0, 0);
            System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
        }
    }

    static void dfs(int idx, int depth) {
        if (depth == M) {
            spread_virus(emptySpace);
            return;
        }

        for (int i = idx; i < inactive_virus_list.size(); i++) {
            Node inactive_virus = inactive_virus_list.get(i);
            active_virus_list.add(inactive_virus);
            dfs(i + 1, depth + 1);
            active_virus_list.remove(inactive_virus);
        }
    }

    private static void spread_virus(int emptySpace) {
        int time = 1;
        boolean[][] infected = new boolean[N][N];
        Queue<Node> queue = new LinkedList<>(active_virus_list);

        for (Node virus : active_virus_list) {
            infected[virus.x][virus.y] = true;
        }

        while (!queue.isEmpty()) {
            int size = queue.size();

            //bfs 탐색
            for (int i = 0; i < size ; i++){
                Node node = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int tempX = node.x + dx[d];
                    int tempY = node.y + dy[d];

                    if (!isValid(tempX, tempY, infected)) {
                        continue;
                    }

                    if (arr[tempX][tempY] == 0) {
                        emptySpace--;
                    }

                    if (emptySpace == 0) {
                        answer = Math.min(time, answer);
                        return;
                    }

                    queue.add(new Node(tempX, tempY));
                    infected[tempX][tempY] = true;
                }
            }
            time++;
        }
    }

    private static boolean isValid(int x, int y, boolean[][] infected) {
        return x >= 0 && y >= 0 && x < N && y < N && arr[x][y] != 1 && !infected[x][y];
    }

    public static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
