package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ans2606_bfs {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int linkN = Integer.parseInt(bf.readLine());

        int[][] map = new int[n][n];
        boolean[] visited = new boolean[n];

        for(int i = 0; i < linkN; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a-1][b-1] = map[b-1][a-1] = 1;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;

        int answer = 0;
        while(!queue.isEmpty()){
            int node = queue.poll();

            for(int i = 0; i < n ; i++){
                if(map[node][i] == 1 && !visited[i]){
                    queue.add(i);
                    answer++;
                    visited[i] = true;
                }
            }
        }

        System.out.println(answer);
    }
}
