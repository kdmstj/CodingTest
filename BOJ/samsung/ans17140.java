package BOJ.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ans17140 {

    static int[][] arr;
    static int time = 0;
    static int maxR = 3;
    static int maxC = 3;

    public static void main(String[] args) throws IOException {

        arr = new int[100][100];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < maxR; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < maxC; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (arr[r][c] != k) {
            time++;

            if (time > 100) {
                time = -1;
                break;
            }

            System.out.println("maxR : " + maxR + " maxC : " + maxC);
            if (maxR >= maxC) {
                System.out.println("doR");
                calculateR();
            } else {
                System.out.println("doC");
                calculateC();
            }
        }

        System.out.println(time);
    }

    private static void calculateR() {
        int limit = maxC;
        maxC = Integer.MIN_VALUE;
        for (int i = 0; i < maxR; i++) {
            //각 행마다 정렬 진행
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < limit; j++) {
                if (arr[i][j] == 0) break;

                map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
            }
            List<Map.Entry<Integer, Integer>> entryList = sort(map);

            //새로운 배열 할당
            int index = 0;
            int[] temp = new int[100];
            for (Map.Entry<Integer, Integer> entry : entryList) {
                if (index >= 100) break;
                temp[index++] = entry.getKey();
                temp[index++] = entry.getValue();
            }

            arr[i] = temp;
            maxC = Math.max(maxC, index);
        }
    }

    private static void calculateC() {
        int limit = maxR;
        maxR = Integer.MIN_VALUE;
        for (int i = 0; i < maxC; i++) {
            //각 열마다 수행
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < limit; j++) {
                if(arr[j][i] == 0) continue;
                map.put(arr[j][i], map.getOrDefault(arr[j][i], 0) + 1);
                System.out.println("key : " + arr[j][i] + "value : " + map.get(arr[j][i]));
            }

            List<Map.Entry<Integer, Integer>> entryList = sort(map);


            //새로운 배열 할당하기
            int index = 0;
            int[] temp = new int[100];
            for (Map.Entry<Integer, Integer> entry : entryList) {
                if (index >= 100) break;
                temp[index++] = entry.getKey();
                temp[index++] = entry.getValue();
            }

            for (int l = 0; l < temp.length; l++) {
                if(temp[l] == 0) break;
                arr[l][i] = temp[l];
            }

            maxR = Math.max(maxR, index);
        }
    }

    private static List<Map.Entry<Integer, Integer>> sort(Map<Integer, Integer> map) {
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort((entry1, entry2) -> {
            int countComparison = Integer.compare(entry1.getValue(), entry2.getValue());
            if (countComparison == 0) {
                return Integer.compare(entry1.getKey(), entry2.getKey());
            }
            return countComparison;
        });
        return entryList;
    }
}