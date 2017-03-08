/*
INPUT
2
10 3
3 3 3 1 2  3 2 2 2 1
9 3
1 744 755 4 897 902 890 6 777

OUTPUT
0
651
 */

import java.util.Arrays;
import java.util.Scanner;

//TODO: 풀이에 나와있는 방식을 이용해서 얼마나 더 빨라지나 확인해보기
public class QUANTIZE {
    public static int n;
    public static int s;
    public static int[] series;
    public static int[][] cache;

    public static int squareSum(int from, int to, int x) {
        int ret = 0;

        for(int i = from; i <= to; i++)
            ret += (x - series[i]) * (x - series[i]);

        return ret;
    }

    public static int findMinPoint(int from, int to, int lower, int upper) {
        int mid = (lower + upper) / 2;
        int midSum = squareSum(from, to, mid);

        if(squareSum(from, to, mid-1) < midSum) return findMinPoint(from, to, lower, mid-1);
        else if(squareSum(from, to, mid+1) < midSum) return findMinPoint(from, to, mid+1, upper);
        return mid;
    }

    public static int minError(int from, int to) {
        return squareSum(from, to, findMinPoint(from, to, series[from], series[to]));
    }

    public static int quantize(int from, int size) {

        //base case1: cache에 초기값이 아닌 숫자가 있는 경우
        if(cache[from][size] != -1) return cache[from][size];

        //base case2: 양자화 가능한 묶음이 하나인 경우
        if(size == 1) return cache[from][size] = minError(from, n-1);

        int ret = 987654321;

        for(int to = from; to < n - 1; to++) {
            ret = Math.min(ret, minError(from, to) + quantize(to+1, size-1));
        }

        return cache[from][size] = ret;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int C = sc.nextInt();

        for(int i = 0; i < C; i++) {
            n = sc.nextInt();
            s = sc.nextInt();

            series = new int[n];
            cache = new int[n][s+1];

            for(int j = 0; j < n; j++)
                series[j] = sc.nextInt();

            Arrays.sort(series);

            for(int j = 0; j < n; j++) {
                for(int k = 0; k <= s; k++) {
                    cache[j][k] = -1;
                }
            }

            int minValue = Integer.MAX_VALUE;
            int newValue;

            for(int j = 1; j <= s; j++) {
                newValue = quantize(0, j);
                minValue = Math.min(minValue, newValue);
            }

            System.out.println(minValue);
        }
    }
}