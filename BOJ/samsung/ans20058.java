package BOJ.samsung;

import java.util.*;
import java.io.*;

public class ans20058 {

    public static int N;
    public static int powN;
    public static int Q;
    public static int[][] A;
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        powN = (int) Math.pow(2, N);
        A = new int[powN][powN];

        //input - 배열 A 얼음양
        for (int i = 0; i < powN; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < powN; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //input - 시전한 단계 L1, ... LQ & A[r][c] 시전하기
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < Q; i++){
            int L = Integer.parseInt(st.nextToken());
            //1. 돌리기
            check1(L);
            //2. 얼음이 있는 칸 3개 또는 그 이상과 인접하지 않는 칸의 얼음의 양은 줄어든다.
            check2();
        }


        //output - 남아있는 A[r][c] 의 합
        getAnswer1();
        //output - 가장 큰 덩어라가 차지하는 칸의 개수 - 단 덩어리가 없으면 0을 출력한다.
        getAnswer2();
    }

    public static void check1(int L) {
        int len = (int) Math.pow(2, L);
        for (int i = 0; i < powN; i += len) {
            for (int j = 0; j < powN; j += len) {
                rotate(i, j, L);
            }
        }
    }

    public static void rotate(int r, int c, int L) {
        int len = (int) Math.pow(2, L);
        int[][] temp = new int[len][len];

        // 회전 시키기
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                temp[j][len - 1 - i] = A[r + i][c + j];
            }
        }

        // 원래 배열에 덮어쓰기
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                A[r + i][c + j] = temp[i][j];
            }
        }
    }

    public static void check2(){
        int[][] copy = new int[powN][powN];
        for(int r = 0; r < powN; r++){
            for(int c = 0; c < powN; c++){
                int count = 0;
                for(int d = 0; d < 4; d++){
                    int tempX = r + dx[d];
                    int tempY = c + dy[d];
                    if(!isOutOfBound(tempX, tempY) && A[tempX][tempY] > 0) count++;
                }
                if(count < 3){
                    copy[r][c] = Math.max(0, A[r][c] - 1);
                }else {
                    copy[r][c] = A[r][c];
                }
            }
        }

        A = copy;
    }

    public static void getAnswer1(){
        int total = 0;
        for(int i = 0; i < powN; i++){
            for(int j = 0; j < powN; j++){
                total += A[i][j];
            }
        }
        System.out.println(total);
    }

    public static void getAnswer2(){
        boolean[][] visited = new boolean[powN][powN];
        int max_dump = Integer.MIN_VALUE;
        for(int i = 0; i < powN; i++){
            for(int j = 0; j < powN; j++){
                if(!visited[i][j] && A[i][j] > 0){
                    int sum = 0;
                    Queue<int[]> queue = new LinkedList();
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                    while(!queue.isEmpty()){
                        int[] element = queue.poll();
                        sum++;
                        for(int d = 0; d < 4; d++){
                            int tempX = element[0] + dx[d];
                            int tempY = element[1] + dy[d];
                            if(!isOutOfBound(tempX, tempY) && !visited[tempX][tempY] && A[tempX][tempY] > 0){
                                queue.add(new int[]{tempX, tempY});
                                visited[tempX][tempY] = true;
                            }
                        }
                    }
                    max_dump = Math.max(max_dump, sum);
                }
            }
        }
        System.out.println(max_dump == Integer.MIN_VALUE ? 0 : max_dump);
    }

    public static boolean isOutOfBound(int x, int y){
        return x < 0 || y < 0 || x >= powN || y >= powN;
    }
}
