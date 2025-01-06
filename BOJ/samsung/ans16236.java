package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ans16236 {

    static int N;
    static int[][] arr;
    static List<Fish> fish_list;
    static Fish shark;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        fish_list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int element = Integer.parseInt(st.nextToken());
                if (element == 9) {
                    shark = new Fish(i, j, 2);
                } else if (element > 0) {
                    arr[i][j] = element;
                    fish_list.add(new Fish(i, j, element));
                }
            }
        }

        int answer = getAnswer();

        System.out.println(answer);
    }


    private static int getAnswer() {
        int totalTime = 0;
        int eatenFish = 0;

        while (!fish_list.isEmpty()) {
            fish_list.sort(Comparator.comparingInt((Fish f) -> f.x).thenComparingInt(f -> f.y));
            int time = Integer.MAX_VALUE;
            int idx = 0;
            int tempX = shark.x;
            int tempY = shark.y;

            for (int i = 0; i < fish_list.size(); i++) {
                Fish fish = fish_list.get(i);
                if (fish.size >= shark.size) {
                    continue;
                }
                int moveTime = move(fish.x, fish.y, shark.x, shark.y);
                if (moveTime < time) {
                    tempX = fish.x;
                    tempY = fish.y;
                    time = moveTime;
                    idx = i;
                }
            }


            if(time == Integer.MAX_VALUE){
                break;
            }

            totalTime += time;
            eatenFish++;
            if (eatenFish == shark.size) {
                shark.size++;
                eatenFish = 0;
            }
            shark.x = tempX;
            shark.y = tempY;
            fish_list.remove(idx);
            arr[shark.x][shark.y] = 0;
        }
        return totalTime;
    }

    static int move(int fish_x, int fish_y, int current_x, int current_y) {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{current_x, current_y, 0});
        visited[current_x][current_y] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int t = current[2];

            if (x == fish_x && y == fish_y) {
                return t;
            }

            for (int i = 0; i < 4; i++) {
                int tempX = x + dx[i];
                int tempY = y + dy[i];
                if (isInBounds(tempX, tempY) && shark.size >= arr[tempX][tempY] && !visited[tempX][tempY]) {
                    visited[tempX][tempY] = true;
                    queue.offer(new int[]{tempX, tempY, t+1});
                }
            }
        }

        return Integer.MAX_VALUE;
    }


    static class Fish {
        int x;
        int y;
        int size;

        Fish(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }

    static boolean isInBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}

