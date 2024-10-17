package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class ans2606_dfs {

    static int answer2;

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

//        stack_dfs(n, map, visited);
        dfs(0, map, visited);
        System.out.println(answer2);
    }

    private static void stack_dfs(int n, int[][] map, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        visited[0] = true;
        for(int i = 0; i < n; i++){
            if(map[0][i] == 1){
                stack.add(i);
                visited[i] = true;
            }
        }

        int answer = 0;
        while(!stack.isEmpty()){
            int current = stack.pop();
            answer++;
            for(int i = 0; i < n; i++){
                if(map[current][i] == 1 && !visited[i]){
                    stack.add(i);
                    visited[i] = true;
                }
            }
        }

        System.out.println(answer);
    }

    private static void dfs(int x, int[][] map, boolean[] visited){
        visited[x] = true;

        for(int i = 0; i < map.length; i++){
            if(map[x][i] == 1 && !visited[i]){
                answer2++;
                dfs(i, map, visited);
            }
        }
    }
}
