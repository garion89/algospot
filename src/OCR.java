import java.util.Scanner;

/*
INPUT
5 3
I am a boy buy
1.0 0.0 0.0 0.0 0.0
0.1 0.6 0.1 0.1 0.1
0.1 0.1 0.6 0.1 0.1
0.1 0.1 0.1 0.6 0.1
0.2 0.2 0.2 0.2 0.2
0.2 0.2 0.2 0.2 0.2
0.8 0.1 0.0 0.1 0.0
0.1 0.7 0.0 0.2 0.0
0.0 0.1 0.8 0.0 0.1
0.0 0.0 0.0 0.5 0.5
0.0 0.0 0.0 0.5 0.5
4 I am a buy
4 I I a boy
4 I am am boy

OUTPUT
I am a boy
I am a boy
I am a boy
 */

//TODO: 나중에 풀 문제
public class OCR {
    public static int m, q;
    public static String[] voca;

    public static double[] B;
    public static double[][] T;
    public static double[][] M;

    public static String[] ocr(String[] Q) {
        return new String[3];
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        m = sc.nextInt();
        q = sc.nextInt();

        voca = new String[m];
        for(int i = 0; i < m; i++) {
            voca[i] = sc.next();
        }

        B = new double[m];

        T = new double[m][m];

        M = new double[m][m];

        for(int i = 0; i < m; i++)
            B[i] = sc.nextDouble();

        for(int i = 0; i < m; i++)
            for(int j = 0; j < m; j++)
                T[i][j] = sc.nextDouble();
        for(int i = 0; i < m; i++)
            for(int j = 0; j < m; j++)
                M[i][j] = sc.nextDouble();

        for(int i = 0; i < q; i++) {
            int num = sc.nextInt();
            String[] P = new String[num];

            for(int j = 0; j < num; j++)
                P[j] = sc.next();

            P = ocr(P);

            for(int j = 0; j < num; j++)
                System.out.print(P[j] + " ");

            System.out.println();
        }


        /* test input process
        for(int i = 0; i < m; i++) {
            System.out.print(voca[i] + " ");
        }

        System.out.println();


        for(int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++)
                System.out.print(T[i][j] + " ");

            System.out.println();
        }

        for(int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++)
                System.out.print(M[i][j] + " ");

            System.out.println();
        }
        */

    }
}