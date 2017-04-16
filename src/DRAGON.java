import java.util.Scanner;

/*
INPUT
4
0 1 2
1 1 5
2 6 5
42 764853475 30

1
2 6 5

OUTPUT
FX
FX+YF
+FX-Y
FX-YF-FX+YF+FX-YF-FX+YF-FX-YF-
 */

public class DRAGON {
    public static final String X = "X+YF";
    public static final String Y = "FX-Y";
    public static final String Zero = "FX";

    public static final int MAX = 1000000000 + 1;

    public static int[] length;

    public static char expand(String dragonCurve, int generations, int skip) {
        if(generations == 0) {
            assert skip < dragonCurve.length();
            return dragonCurve.charAt(skip);
        }

        for(int i = 0; i < dragonCurve.length(); i++) {
            if(dragonCurve.charAt(i) == 'X' || dragonCurve.charAt(i) == 'Y') {
                if(skip >= length[generations])
                    skip -= length[generations];
                else if(dragonCurve.charAt(i) == 'X')
                    return expand(X, generations-1, skip);
                else
                    return expand(Y, generations-1, skip);
            } else if(skip > 0)
                skip--;
            else
                return dragonCurve.charAt(i);
        }

        return '#';
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n, p, l;

        length = new int[51];
        length[0] = 1;
        for(int i = 1; i <= 50; i++) {
            length[i] = Math.min(MAX, length[i-1] * 2 + 2);
        }

        int C = sc.nextInt();
        while (C --> 0) {
            n = sc.nextInt();
            p = sc.nextInt();
            l = sc.nextInt();

            for(int i = 0; i < l; i++)
                System.out.print(expand("FX", n, p+i-1));

            System.out.println();
        }
    }
}