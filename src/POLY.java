/*
INPUT
3
2
4
92

OUTPUT
2
19
4841817
 */

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Arrays;

public class POLY {

    public static int[][] cache;
    public static int n;

    public static final int MOD = 10000000;

    //바로 밑에 층의 블락의 개수와 남은 블락의 개수를 가지고 몇개의 세로 단조 폴리를 만들 수 있는지 알려주는 함수
    public static int count(int bottom, int block) {

        if(cache[bottom][block] != -1) return cache[bottom][block];

        //base case: 남은 블락이 없는 경우
        if(block == 0) return 1;

        int ret = 0;
        for(int i = 1; i <= block; i++) {
            ret += (bottom + i - 1) * count(i, block - i);
            ret = ret >= MOD ? ret % MOD : ret;
        }

        return cache[bottom][block] = ret;
    }

    public static int poly(int n) {
        int ret = 0;
        for(int i = 1; i <= n; i++) {
            ret += count(i, n - i);
            ret = ret >= MOD ? ret % MOD : ret;
        }
        return ret;
    }

    public static void main(String args[]) throws IOException {

        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);

        int C = Integer.parseInt(br.readLine().trim());
        while(C --> 0){
            n = Integer.parseInt(br.readLine().trim());
            cache = new int[n+1][n+1];
            for(int i = 0 ; i < cache.length; i++)
                Arrays.fill(cache[i], -1);
            System.out.println(poly(n));
        }
    }
}