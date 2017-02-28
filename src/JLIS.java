/*
INPUT
3
3 3
1 2 4
3 4 7
3 3
1 2 3
4 5 6
5 3
10 20 30 1 2
10 20 30

1
3 3
1 2 4
3 4 7

OUTPUT
5
6
5
 */

import java.util.Scanner;

public class JLIS {

    public static int N;
    public static int M;
    public static int[] A;
    public static int[] B;
    public static int[][] cache;

    //A위치 a, B위치 b에서 시작하는 합친 LIS 최대값
    public static int jlis(int a, int b) {

        //base case1: memoization
        if(cache[a][b] != -1) return cache[a][b];

        int ret = 0;

        int currValue = Math.max(A[a],B[b]);

        for(int na = a + 1; na < N; na++) {
            if(currValue < A[na])
                ret = Math.max(ret, 1 + jlis(na, b));
        }

        for(int nb = b + 1; nb < M; nb++) {
            if(currValue < B[nb])
                ret = Math.max(ret, 1 + jlis(a, nb));
        }

        return cache[a][b] = ret;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int C = sc.nextInt();


        for(int i = 0; i < C; i++) {
            N = sc.nextInt() + 1;
            M = sc.nextInt() + 1;

            //cache initialization
            cache = new int[N][M];
            for(int a = 0; a < N; a++) {
                for(int b = 0; b < M; b++) {
                    cache[a][b] = -1;
                }
            }

            //get input A and B
            A = new int[N];
            B = new int[M];
            A[0] = Integer.MIN_VALUE;
            B[0] = Integer.MIN_VALUE;

            for(int j = 1; j < N; j++)
                A[j] = sc.nextInt();

            for(int j = 1; j < M; j++)
                B[j] = sc.nextInt();

            System.out.println(jlis(0, 0));
        }
    }
}