package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ans17837 {

    static int N;
    static int K;
    static int[][] color;
    static Stack<Integer>[][] chess;
    static Node[] node_list;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        chess = new Stack[N][N];
        color = new int[N][N]; // 0:흰, 1:빨, 2: 파
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                color[i][j] = Integer.parseInt(st.nextToken());
                chess[i][j] = new Stack<Integer>();
            }
        }

        node_list = new Node[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            if(d == 1){
                d = 0;
            } else if(d == 2){
                d = 2;
            } else if (d == 3) {
                d = 1;
            } else {
                d = 3;
            }

            node_list[i] = new Node(x, y, d);
            chess[x][y].add(i);
        }



        int turn = 0;
        while (true) {

            //절대로 게임이 종료되지 않는 경우 || 1,000보다 큰 경우
            if (turn >= 1000) {
                turn = -1;
                break;
            }

            for (int i = 0; i < K; i++) {
                Node node = node_list[i];

                int x = node.x;
                int y = node.y;
                int d = node.d;

                int tempX = x + dx[node.d];
                int tempY = y + dy[node.d];

                if (isOutOfRange(tempX, tempY) || isBlue(tempX, tempY)) {
                    int tempD = (d + 2) % 4;

                    tempX = x + dx[tempD];
                    tempY = y + dy[tempD];
                    node.d = tempD;

                    if(isOutOfRange(tempX, tempY) || isBlue(tempX, tempY)){
                        //방향 전환한 것에 대해서도 막혀있을 경우
                        continue;
                    }
                }


                if (isWhite(tempX, tempY)) {
                    //node 위에 있는 말들도 함께 같이 이동한다.
                    move(i, x, y, tempX, tempY);
                    if(chess[tempX][tempY].size() >= 4) {
                        System.out.println(turn + 1);
                        return;
                    }
                } else if (isRed(tempX, tempY)) {
                    //node 위에 있는 말들도 함께 이동한다. (대신 순서를 바꾼다.)
                    move_reverse(i, x, y, tempX, tempY);
                    if(chess[tempX][tempY].size() >= 4) {
                        System.out.println(turn + 1);
                        return;
                    }
                }
            }


            turn++;
        }

        System.out.println(turn);
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
            node_list[num].x = tempX;
            node_list[num].y = tempY;
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
            node_list[num].x = tempX;
            node_list[num].y = tempY;
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

    static class Node {
        private int x;
        private int y;
        private int d;

        Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
