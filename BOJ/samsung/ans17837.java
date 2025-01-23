package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class ans17837 {

    static final int MAX_TURN = 1000;

    static int N, K;
    static int[][] color;
    static Stack<Integer>[][] chess;
    static Node[] nodeList;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        chess = new Stack[N][N];
        color = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                color[i][j] = Integer.parseInt(st.nextToken());
                chess[i][j] = new Stack<>();
            }
        }

        nodeList = new Node[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = convertDirection(Integer.parseInt(st.nextToken()));

            nodeList[i] = new Node(x, y, d);
            chess[x][y].add(i);
        }


        int turn = playGame();
        System.out.println(turn);
    }

    private static int playGame() {
        for (int turn = 1; turn <= MAX_TURN; turn++) {
            for (int i = 0; i < K; i++) {
                if (movePiece(i)) {
                    return turn;
                }
            }
        }
        return -1;
    }

    private static boolean movePiece(int pieceIdx) {
        Node node = nodeList[pieceIdx];

        int x = node.x, y = node.y, d = node.d;

        int nX = x + dx[node.d];
        int nY = y + dy[node.d];

        if (isOutOfRange(nX, nY) || isBlue(nX, nY)) {

            node.reverseDirection();
            nX = x + dx[node.d];
            nY = y + dy[node.d];

            if (isOutOfRange(nX, nY) || isBlue(nX, nY)) {
                return false; //이동하지 않음.
            }
        }


        if (isWhite(nX, nY)) {
            move(pieceIdx, x, y, nX, nY);
        } else if (isRed(nX, nY)) {
            move_reverse(pieceIdx, x, y, nX, nY);
        }

        return chess[nX][nY].size() >= 4;
    }

    private static void move_reverse(int i, int x, int y, int tempX, int tempY) {
        Queue<Integer> temp_queue = new LinkedList<>();
        while (true) {
            int num = chess[x][y].pop();
            temp_queue.add(num);
            if (num == i) break;
        }

        while (!temp_queue.isEmpty()) {
            int num = temp_queue.poll();
            chess[tempX][tempY].add(num);
            nodeList[num].x = tempX;
            nodeList[num].y = tempY;
        }
    }

    private static void move(int i, int x, int y, int tempX, int tempY) {
        Stack<Integer> temp_stack = new Stack<>();
        while (true) {
            int num = chess[x][y].pop();
            temp_stack.add(num);
            if (num == i) break;
        }

        while (!temp_stack.isEmpty()) {
            int num = temp_stack.pop();
            chess[tempX][tempY].add(num);
            nodeList[num].x = tempX;
            nodeList[num].y = tempY;
        }
    }

    private static boolean isBlue(int tempX, int tempY) {
        return color[tempX][tempY] == 2;
    }

    private static boolean isRed(int tempX, int tempY) {
        return color[tempX][tempY] == 1;
    }

    private static boolean isWhite(int tempX, int tempY) {
        return color[tempX][tempY] == 0;
    }

    private static boolean isOutOfRange(int tempX, int tempY) {
        return tempX < 0 || tempY < 0 || tempX >= N || tempY >= N;
    }

    private static int convertDirection(int d) {
        if(d == 1){
            d = 0;
        } else if(d == 3){
            d = 1;
        } else if(d == 4){
            d = 3;
        }

        return d;
    }

    static class Node {
        private int x;
        private int y;
        private int d;

        Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        void reverseDirection() {
            this.d = (this.d + 2) % 4;
        }
    }
}
