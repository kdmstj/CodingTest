package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ans3190 {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        boolean[][] apple = new boolean[N][N];
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            apple[x][y] = true;
        }

        int L = Integer.parseInt(br.readLine());
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            String c = st.nextToken();
            map.put(x, c);
        }

        int headX = 0, headY = 0;
        Queue<int[]> queue = new LinkedList<>(); //뱀의 머리~꼬리까지 담긴 큐
        queue.add(new int[]{headX, headY});
        boolean[][] visited = new boolean[N][N];
        visited[headX][headY] = true;

        int time = 0; //이동한 시간
        int d = 0; //방향

        while (true) {
            time++;
            headX += dx[d];
            headY += dy[d];

            if (isFinish(N, headX, headY, visited)) break;

            //머리 이동
            queue.add(new int[]{headX, headY});
            visited[headX][headY] = true;

            //사과가 없다면, 꼬리 축소
            if (!apple[headX][headY]) {
                int[] tail = queue.poll();
                visited[tail[0]][tail[1]] = false;
            } else {//사과가 있다면 먹어야해.
                apple[headX][headY] = false;
            }

            //방향 전환해야 하는지 여부
            if(map.containsKey(time)){
                String c = map.get(time);
                if (c.equals("D")) {//이동 후 오른쪽으로 방향 전환
                    d = (d + 1) % 4;
                } else {//이동 후 왼쪽으로 방향 전환
                    d = (d + 3) % 4;
                }
            }
        }
        System.out.println(time);
    }

    private static boolean isFinish(int N, int headX, int headY, boolean[][] visited) {
        if (headX < 0 || headX >= N || headY < 0 || headY >= N) {
            return true;
        }

        if(visited[headX][headY]){
            return true;
        }

        return false;
    }
}
