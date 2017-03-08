import java.io.IOException;
import java.util.Scanner;

/*
INPUT
2
5 2 0
0 1 1 1 0
1 0 0 0 1
1 0 0 0 0
1 0 0 0 0
0 1 0 0 0
3
0 2 4
8 2 3
0 1 1 1 0 0 0 0
1 0 0 1 0 0 0 0
1 0 0 1 0 0 0 0
1 1 1 0 1 1 0 0
0 0 0 1 0 0 1 1
0 0 0 1 0 0 0 1
0 0 0 0 1 0 0 0
0 0 0 0 1 1 0 0
4
3 1 2 6

OUTPUT
0.83333333 0.00000000 0.16666667
0.43333333 0.06666667 0.06666667 0.06666667
 */

public class NUMB3RS {
    public static int n, d, p, t;
    public static int[][] links;
    public static double[][] probLinks;
    public static double[] probs;

    public static void dayGo() {
        double[] after = new double[n];

        for(int i = 0; i < n; i++)
            after[i] = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                after[i] += probs[j] * probLinks[j][i];
            }
        }

        probs = after;
    }

    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);

        int C = sc.nextInt();
        while (C --> 0) {
            n = sc.nextInt();
            d = sc.nextInt();
            p = sc.nextInt();

            links = new int[n][n+1];
            for(int i = 0; i < n; i++) {
                int temp = 0;
                for (int j = 0; j < n; j++) {
                    links[i][j] = sc.nextInt();
                    temp += links[i][j];
                }
                links[i][n] = temp;
            }

            probLinks = new double[n][n];
            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++)
                    probLinks[i][j] = (double) links[i][j] / links[i][n];

            probs = new double[n];
            for(int i = 0; i < n; i++)
                probs[i] = 0;

            probs[p] = 1;

            while(d --> 0) {
                dayGo();
            }

            t = sc.nextInt();
            int q;

            while(t --> 0) {
                q = sc.nextInt();
                System.out.print(probs[q]);
                System.out.print(" ");
            }
            System.out.println();

        }
    }
}