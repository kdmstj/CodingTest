package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ans15683 {

    static int N;
    static int M;
    static int answer = Integer.MAX_VALUE;
    static List<Node> nodeList = new ArrayList<>();
    static int[] dx = {0, -1, 0, 1}; //오른쪽 , 위쪽, 왼쪽, 아래쪽
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (isCCTV(map[i][j])) {
                    nodeList.add(new Node(i, j, map[i][j]));
                }
            }
        }

        dfs(0, map);
        System.out.println(answer);
    }

    private static void dfs(int count, int[][] map) {
        if (count == nodeList.size()) {
            int blindSpot = getBlindSpot(map);
            answer = Math.min(blindSpot, answer);
            return;
        }

        Node node = nodeList.get(count);
        switch (node.type) {
            case 1:
                for (int i = 0; i < 4; i++) {
                    int[][] copyMap = copyMap(map);
                    go(copyMap, node.x, node.y, i);
                    dfs(count + 1, copyMap);
                }
                break;
            case 2:
                for (int i = 0; i < 2; i++) {
                    int[][] copyMap = copyMap(map);
                    go(copyMap, node.x, node.y, i);
                    go(copyMap, node.x, node.y, i + 2);
                    dfs(count + 1, copyMap);
                }
                break;
            case 3:
                for (int i = 0; i < 4; i++) {
                    int[][] copyMap = copyMap(map);
                    go(copyMap, node.x, node.y, i % 4);
                    go(copyMap, node.x, node.y, (i + 1) % 4);
                    dfs(count + 1, copyMap);
                }
                break;
            case 4:
                for (int i = 0; i < 4; i++) {
                    int[][] copyMap = copyMap(map);
                    go(copyMap, node.x, node.y, i % 4);
                    go(copyMap, node.x, node.y, (i + 1) % 4);
                    go(copyMap, node.x, node.y, (i + 2) % 4);
                    dfs(count + 1, copyMap);
                }
                break;
            case 5:
                int[][] copyMap = copyMap(map);
                for (int i = 0; i < 4; i++) {
                    go(copyMap, node.x, node.y, i);
                }
                dfs(count + 1, copyMap);
                break;
        }
    }

    private static int[][] copyMap(int[][] originMap) {
        int[][] copyMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = originMap[i][j];
            }
        }

        return copyMap;
    }

    private static int getBlindSpot(int[][] map) {
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    answer++;
                }
            }
        }

        return answer;
    }

    private static void go(int[][] map, int nodeX, int nodeY, int direction) {
        while (true) {
            nodeX += dx[direction];
            nodeY += dy[direction];
            if (isOutOfBound(nodeX, nodeY) || map[nodeX][nodeY] == 6) {
                break;
            }

            if (isCCTV(map[nodeX][nodeY])) {
                continue;
            }

            map[nodeX][nodeY] = -1;
        }
    }

    private static boolean isCCTV(int num) {
        return num >= 1 && num <= 5;
    }

    private static boolean isOutOfBound(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }

    private static class Node {
        int x;
        int y;
        int type;

        Node(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
}
