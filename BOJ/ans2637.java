package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ans2637 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] answer = new int[N+1]; //필요한 부품의 수
        int[] inDegree = new int[N+1]; //진입차수 배열
        int[][] graph = new int[N+1][N+1]; //몇개의 부품이 필요한지 가중
        boolean[] isComb = new boolean[N+1];
        for(int i = 0; i < M ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            graph[X][Y] = K; //Y 가 K 가 모여서 X가 된다.
            inDegree[Y]++;
            isComb[X] = true;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i < N+1 ; i++){
            if(inDegree[i] == 0){
                queue.add(i);
                answer[i] = 1;
            }
        }

        while(!queue.isEmpty()){
            int node = queue.poll();
            for(int i = 1; i < N+1; i++){
                if(graph[node][i] > 0){
                    answer[i] += answer[node] * graph[node][i];
                    inDegree[i]--;

                    if(inDegree[i] == 0){
                        queue.add(i);
                    }
                }
            }
        }

        for (int i = 1; i < N+1 ; i++) {
            if (!isComb[i])
                System.out.print(i + " " + answer[i] + "\n");
        }
    }
}
