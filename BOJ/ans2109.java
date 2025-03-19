package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ans2109 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N= Integer.parseInt(br.readLine());

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            int result = a[0] - b[0]; //d 를 기준으로 오름차순 정렬
            return result == 0 ? b[1] - a[1] : result; //p를 기준으로 내림차순 정렬
        });

        while(N-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            pq.add(new int[]{d, p});
        }

        PriorityQueue<Integer> selectPq = new PriorityQueue<>();
        while(!pq.isEmpty()){
            int[] univ = pq.poll();
            int d = univ[0];
            int p = univ[1];
            if(selectPq.size() < d){
                selectPq.add(p);
            } else {
                if(!selectPq.isEmpty() && selectPq.peek() < p){
                    selectPq.poll();
                    selectPq.add(p);
                }
            }
        }

        int answer = 0;
        for(int p : selectPq){
            answer += p;
        }
        System.out.println(answer);
    }
}
