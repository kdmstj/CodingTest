package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ans16234 {

    static int N;
    static int L;
    static int R;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0}; //상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Node> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while (move()) {
            day++;
        }

        System.out.println(day);
    }

    static boolean move() {
        boolean move = false;

        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    int sum = bfs(i, j);
                    if (list.size() > 1) {
                        changePopulation(sum);
                        move = true;
                    }
                }
            }
        }

        return move;
    }

    static int bfs(int x, int y) {
        Queue<Node> queue = new LinkedList<>();
        list = new ArrayList<>(); //변경해야 할 노드가 있는 리스트

        addNode(x, y, queue);

        int sum = arr[x][y];
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tempX = node.x + dx[i];
                int tempY = node.y + dy[i];
                if (tempX < 0 || tempY < 0 || tempX >= N || tempY >= N || visited[tempX][tempY]) {
                    continue;
                } else {
                    int diff = Math.abs(arr[tempX][tempY] - arr[node.x][node.y]);
                    if (diff >= L && diff <= R) {
                        addNode(tempX, tempY, queue);
                        sum += arr[tempX][tempY];
                    }
                }
            }
        }
        return sum;
    }

    private static void addNode(int x, int y, Queue<Node> queue) {
        queue.add(new Node(x, y));
        visited[x][y] = true;
        list.add(new Node(x, y));
    }

    static void changePopulation(int sum) {
        int avg = sum / list.size();
        for (Node node : list) {
            arr[node.x][node.y] = avg;
        }
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

