package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class ans1931 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        int[][] arr = new int[N][2];
        for(int i = 0; i < N ; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); //시작시간
            arr[i][1] = Integer.parseInt(st.nextToken()); //종료시간
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]){
                    return o1[0] - o2[0]; //종료 시간이 같다면, 시작 시간을 기준으로 오름차순 정렬을 한다.
                }
                return o1[1] - o2[1];
            }
        });

        int count = 1;
        int endTime = arr[0][1];
        for(int i=1; i < N ; i++){
            if(arr[i][0] < endTime){
                continue;
            }

            count++;
            endTime = arr[i][1];
        }

        System.out.println(count);
    }
}
