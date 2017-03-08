/*
INPUT
3
2
4
92

OUTPUT
0
2
913227494
 */

import java.util.Scanner;

//TODO: 스캐폴딩으로 확인하는거 해보기(263p참고)
public class ASYMTILING {

    public static int[] cache;

    public static final int MOD = 1000000007;

    public static int tiling(int n) {

        if(cache[n] != -1) return cache[n];

        //base case
        if(n < 2) return 1;
        return cache[n] = (tiling(n-1) + tiling(n-2)) % MOD;
    }

    public static int solve(int n) {
        //case1: n is odd
        if(n % 2 == 1) return ((tiling(n) - tiling(n/2) + MOD)) % MOD;
        //case2: n id even
        int ret = tiling(n);
        ret = (ret - tiling(n/2) + MOD) % MOD;
        ret = (ret - tiling(n/2 - 1) + MOD) % MOD;
        return ret;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int C = sc.nextInt();
        int n;
        for(int i = 0; i < C; i++) {
            n = sc.nextInt();
            cache = new int[n+1];
            for(int j = 0; j < n+1; j++) cache[j] = -1;
            System.out.println(solve(n));
        }
    }
}