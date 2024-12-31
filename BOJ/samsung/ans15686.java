package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ans15686 {

    static List<Node> chicken_list = new ArrayList<>();
    static List<Node> home_list = new ArrayList<>();
    static boolean[] visited;
    static int N;
    static int M;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int element = Integer.parseInt(st.nextToken());
                if(element == 2){
                    chicken_list.add(new Node(i, j));
                }

                if (element == 1) {
                    home_list.add(new Node(i, j));
                }
            }
        }

        visited = new boolean[chicken_list.size()];
        dfs(0, 0);
        System.out.println(answer);
    }

    private static void dfs(int count, int index) {
        if (count == M) {
            check();
            return;
        }

        for (int i = index; i < chicken_list.size(); i++) {
            if(!visited[i]){
                visited[i] = true;
                dfs(count+1, i+1);
                visited[i] = false;
            }
        }
    }

    private static void check() {
        int distance_sum = 0;
        for (Node node : home_list) {
            int distance = Integer.MAX_VALUE;
            for (int i = 0; i < chicken_list.size(); i++) {
                if (visited[i]) {
                    Node chicken_node = chicken_list.get(i);
                    distance = Math.min(distance, Math.abs(node.x - chicken_node.x) + Math.abs(node.y - chicken_node.y));
                }
            }
            distance_sum += distance;
        }
        answer = Math.min(distance_sum, answer);
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
