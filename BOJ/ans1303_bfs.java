package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ans1303_bfs {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Character[][] charArr = new Character[m][n];

        for(int i = 0; i < m ; i++){
            String line = bf.readLine();
            for(int j = 0; j < n; j++){
                charArr[i][j] = line.charAt(j);
            }
        }

        int wCount = 0;
        int bCount = 0;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < m ; i++){
            for(int j = 0; j < n; j++){
                if(!visited[i][j]){
                    queue.add(new int[]{i, j});

                    int count = 0;
                    while(!queue.isEmpty()){
                        int[] current = queue.poll();
                        int currentX = current[0];
                        int currentY = current[1];

                        for(int idx = 0; idx < 4 ; idx++){
                            int nextX = currentX + dx[idx];
                            int nextY = currentY + dy[idx];
                            if(nextX < 0 || nextY < 0 || nextX > charArr.length -1 || nextY > charArr[0].length - 1){
                                continue;
                            }

                            if(charArr[nextX][nextY] != charArr[currentX][currentY] || visited[nextX][nextY]){
                                continue;
                            }

                            queue.add(new int[]{nextX, nextY});
                            count++;
                            visited[nextX][nextY] = true;
                        }
                    }

                    if(charArr[i][j] == 'W'){
                        wCount += count * count;
                    } else {
                        bCount += count * count;
                    }
                }
            }
        }

        System.out.println(wCount + " " + bCount);
    }
}
