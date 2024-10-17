package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ans13913 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[100_001];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(a);
        visited[a] = true;
        int depth = 0;

        Map<Integer, Integer> map = new HashMap<>();

        Loop:
        while (!queue.isEmpty()) {

            int size = queue.size();
            Loop2:
            for(int i = 0; i < size ; i++){
                int current = queue.poll();

                if(current == b){
                    break Loop;
                }

                if(current * 2 <= b + 1 && !visited[current * 2]){
                    queue.add(current * 2);
                    map.put(current * 2, current);
                    visited[current * 2] = true;
                }

                if(current - 1 >= 0 && !visited[current - 1]){
                    queue.add(current - 1);
                    map.put(current -1, current);
                    visited[current - 1] = true;
                }

                if(current + 1 <= b && !visited[current + 1]){
                    queue.add(current + 1);
                    map.put(current+1, current);
                    visited[current + 1] = true;
                }
            }
            depth++;
        }

        System.out.println(depth);

        Stack<Integer> stack = new Stack<Integer>();
        int child = b;
        while(true){
            stack.add(child);
            if(!map.containsKey(child)){
                break;
            }
            int parent = map.get(child);
            child = parent;
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }

    }
}
