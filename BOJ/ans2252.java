package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ans2252 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] inDegree = new int[N+1];
        List<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < N+1 ; i++){
            graph.add(new ArrayList<Integer>());
        }

        System.out.println();

        for(int i = 0; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            inDegree[b]++; //진입차수 추가
            graph.get(a).add(b); //인접한 노드 추가
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i < N+1 ; i++){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int edge = queue.poll();
            sb.append(edge).append(" ");
            List<Integer> edgeList = graph.get(edge); //edge 와 인접한 노드

            for(int i = 0; i < edgeList.size(); i++){
                int element = edgeList.get(i);
                inDegree[element]--; //edge 와 인접한 노드의 간선 제거
                if(inDegree[element] == 0){
                    queue.add(element);
                }
            }
        }

        System.out.println(sb.toString());
    }
}
