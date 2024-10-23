package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ans1432 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(); //특정 노드와 인접한 노드 리스트
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        int[] outDegree = new int[N]; //진출차수
        for(int i = 0; i < N ; i++){
            String s = br.readLine();
            for(int j = 0; j < N; j++){
                char c = s.charAt(j);
                if (c == '1') {
                    outDegree[i]++;
                    graph.get(j).add(i);
                }
            }
        }

        System.out.println(Arrays.toString(outDegree));

        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x); //내림차순으로 정렬
        for (int i = 0; i < N; i++) {
            if (outDegree[i] == 0) {
                pq.add(i);
            }
        }

        System.out.println(topologicalSort(graph, outDegree, pq, N));
    }

    private static String topologicalSort(List<ArrayList<Integer>> graph, int[] outDegree, PriorityQueue<Integer> queue, int N) {
        if (queue.size() == 0) {
            return "-1";
        }

        int[] answer = new int[N];
        while (!queue.isEmpty()) {
            int element = queue.poll();
            System.out.println("element : " + element + " N :  " + N );

            answer[element] = N--;
            List<Integer> nodeList = graph.get(element);
            for (int i = 0; i < nodeList.size(); i++) {
                int node = nodeList.get(i);
                outDegree[node]--;

                if (outDegree[node] == 0) {
                    queue.add(node);
                }
            }
        }

        if (Arrays.stream(outDegree).anyMatch(x -> x > 0)) {
            return "-1";
        } else {
            StringBuilder sb = new StringBuilder();
            for(int i= 0; i < answer.length; i++){
                sb.append(answer[i]).append(" ");
            }
            return sb.toString();
        }
    }
}
