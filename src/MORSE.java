import java.util.Arrays;
import java.util.Scanner;

/*
INPUT
3
2 2 4
4 8 13
6 4 1

OUTPUT
o--o
--o-ooo-oooo
------oooo
 */

public class MORSE {
    public static int skip;
    public static int[][] bino;

    public static String kth(int n, int m, int k) {
        if(n == 0) {
            char[] remainder = new char[m];
            Arrays.fill(remainder, 'o');
            return new String(remainder);
        }

        if(k < bino[n+m-1][n-1])
            return "-" + kth(n-1, m, k);
        return "o" + kth(n, m-1, k - bino[n+m-1][n-1]);
    }

    public static void calcBino() {
        bino = new int[201][201];

        for (int i = 0; i < bino.length; i++)
            Arrays.fill(bino[i], 0);

        for(int i = 0; i <= 200; i++) {
            bino[i][0] = bino[i][i] = 1;
            for(int j = 1; j < i; j++)
                bino[i][j] = Math.min(1000000100, bino[i-1][j-1] + bino[i-1][j]);
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        calcBino();
        int C = sc.nextInt();
        while (C --> 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int skip = sc.nextInt() - 1;

            System.out.println(kth(n, m, skip));
        }
    }
}