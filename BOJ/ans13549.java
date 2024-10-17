package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ans13549 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[100_001];


        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]); //시간을 순서대로 내림차순 정렬
        pq.add(new int[]{a, 0});

        int answer = 0;
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentNode = current[0];
            int currentTime = current[1];

            System.out.println("currentNode : " + currentNode + "currentTime : " + currentTime);

            if(currentNode == b){
                answer = currentTime;
                break;
            }

            visited[currentNode] = true;
            if(currentNode*2 <= 100_001 && !visited[currentNode*2]){
                pq.add(new int[]{currentNode*2, currentTime});
            }

            if(currentNode < b && currentNode + 1 <= 100_000 && !visited[currentNode+1]){
                pq.add(new int[]{currentNode+1, currentTime+1});
            }

            if(currentNode - 1 >= 0 && !visited[currentNode-1]){
                pq.add(new int[]{currentNode-1, currentTime+1});
            }
        }

        System.out.println(answer);
    }
}
