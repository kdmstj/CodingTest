package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ans16953 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        Queue<Long> queue = new LinkedList<>();
        queue.add(a);

        System.out.println(bfs(b, queue));
    }

    private static long bfs(long b, Queue<Long> queue) {
        long cnt = 1;
        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i = 0; i < size; i++){
                long number = queue.poll();

                if(number == b){
                    return cnt;
                }

                if(number * 2 <= b){
                    queue.add(number*2);
                }

                if(Long.parseLong(number+"1") <= b){
                    queue.add(Long.parseLong(number+"1"));
                }
            }

            cnt++;
        }

        return -1;
    }
}
