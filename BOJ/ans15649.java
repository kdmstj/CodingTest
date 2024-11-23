package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ans15649 {

    static int N;
    static int M;
    static boolean[] visited;
    static int[] arr;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();
        //1부터 N까지 자연 수 중에서 중복 없이 M 개를 고른 수열

        arr = new int[M];
        visited = new boolean[N+1];
        dfs(0);
        System.out.println(sb.toString());
    }

    static void dfs(int depth){
        System.out.println("arr : " + Arrays.toString(arr) + " depth : " + depth);
        if(depth == M){
            for(int val : arr){
                sb.append(val).append(" ");
            }
            sb.append('\n');
            return;
        }

        for(int i = 1; i <= N; i++){
            if(!visited[i]){
                visited[i] = true;
                arr[depth] = i;
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }
}
