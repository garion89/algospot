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

public class DRAGON_FAIL {
    public static final String X = "X+YF";
    public static final String Y = "FX-Y";
    public static final String Zero = "FX";

    // 문자열을 받아서 해당 문자를 num 횟수만큼 변환 시키는 것
    public static String change(String str, int num) {
        while(num--> 0) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == 'X') {
                    str = str.substring(0, i) + X + str.substring(i + 1);
                    i += 3;
                } else if (str.charAt(i) == 'Y') {
                    str = str.substring(0, i) + Y + str.substring(i + 1);
                    i += 3;
                }
            }
        }
        return str;
    }

    //1. change 'start' to 'from'

    //한 함수만 만들자. start랑 end를 정하자


    public static String getDragonCurve(int n, int start, int length) {
        String Current = Zero;
        int jump = (int) (Math.pow(2, n) * 3 - 2);
        int skip = start - 1;
        int from = 0;
        int to = 0;
        start = 0;

        while(n --> 0) {

            for ( ; from < Current.length(); from++) {
                if (Current.charAt(from) == 'X' || Current.charAt(from) == 'Y') {
                    if(jump <= skip) {
                        skip -= jump;
                        continue;
                    } else {
                        //get start point
                        break;
                    }
                } else {
                    if(1 <= skip) {
                        skip--;
                        continue;
                    } else {
                        break;
                    }
                }
            }

            start = skip;

            to = from;

            for( ; to < Current.length(); to++) {
                if (Current.charAt(to) == 'X' || Current.charAt(to) == 'Y') {
                    if(jump <= skip + length) {
                        skip -= jump;
                        continue;
                    } else {
                        //get start point
                        break;
                    }
                } else {
                    if(1 <= skip + length) {
                        skip--;
                        continue;
                    } else {
                        break;
                    }
                }
            }

            if(from == to)
                Current = Current.charAt(from) == 'X' ? X : Y;
            else {
                Current = Current.substring(from, to);
                Current = change(Current, n);
                break;
            }
        }

        Current = Current.substring(start, start + length);

        return Current;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n, p, l;

        int C = sc.nextInt();
        while (C --> 0) {
            n = sc.nextInt();
            p = sc.nextInt();
            l = sc.nextInt();

            System.out.println(getDragonCurve(n,p,l));
        }
    }
}