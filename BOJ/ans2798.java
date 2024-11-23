package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ans2798 {

    static int answer = Integer.MIN_VALUE;
    static int M;
    static int N;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //카드의 개수
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken()); //5, 6, 7, 8, 9
        }

        dfs(0, 0);
        System.out.println(answer);
    }

    static void dfs(int depth, int sum) {
        if(sum > M) return; //M 보다 크다면, 종료한다.

        if(depth == 3){ //3개를 뽑았다면, 종료한다.
            answer = Math.max(sum, answer);
            return;
        }

        for(int i = 0; i < N; i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(depth+1, sum + arr[i]);
                visited[i] = false;
            }
        }
    }
}
