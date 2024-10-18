package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ans11000 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int[][] arr = new int[N][2];

        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> x[1] - y[1]); //종료시간으로 오름차순 정렬

        for(int i = 0; i < N ; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); //시작시간
            arr[i][1] = Integer.parseInt(st.nextToken()); //종료시간
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0]; //시작시간을 기준으로 오름차순 정렬
            }
        });

        int max = 1;
        pq.add(arr[0]);
        for(int i = 1; i < N ; i++){
            if(pq.peek()[1] <= arr[i][0]){
                pq.poll();
            }

            pq.add(arr[i]);
            max = Math.max(pq.size(), max);
        }

        System.out.println(max);
    }
}
