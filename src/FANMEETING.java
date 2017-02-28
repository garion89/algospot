/*
INPUT
4
FFFMMM
MMMFFF
FFFFF
FFFFFFFFFF
FFFFM
FFFFFMMMMF
MFMFMFFFMMMFMF
MMFFFFFMFFFMFFFFFFMFFFMFFFFMFMMFFFFFFF

1
FFFFM
FFFFFMMMMF

OUTPUT
1
6
2
2
 */

import java.util.Scanner;

// 포기.. 구현을 잘못한거 같은데 뭘 잘못했는지 도저히 모르겠음. 언젠가 기회가 있으면 다시 풀어보자. 알고리즘은 책을 베꼈는데 무언가 잘못되고 있다.ㅠㅠ
public class FANMEETING {

    public static int[] addTo(int[] A, int[] B, int k) {
        int an = A.length;
        int rn = B.length + k;

        if (an < rn) {
            int[] ret = new int[rn];
            for(int i = 0; i < an; i++)
                ret[i] = A[i];
            for(int i = k; i < rn; i++)
                ret[i] += B[i-k];

            return ret;
        } else {
            for(int i = k; i < rn; i++)
                A[i] += B[i-k];

            return A;
        }
    }

    public static int[] subFrom(int[] A, int[] B) {
        int bn = B.length;

        for(int i = 0; i < bn; i++)
            A[i] -= B[i];

        return A;
    }

    public static int[] multiply(int[] A, int[] B) {
        int an = A.length;
        int bn = B.length;

        int[] ret = new int[an+bn-1];

        for(int i = 0; i < an; i++) {
            for (int j = 0; j < bn; j++) {
                ret[i+j] += A[i] * B[j];
            }
        }

        return ret;
    }

    public static int[] karatsuba(int[] A, int[] B) {

        int an = A.length;
        int bn = B.length;

        if(an < bn) return karatsuba(B, A);
        if(an == 0) return A;
        if(an == 1 && A[0] == 0) return A;
        if(bn == 1 && B[0] == 0) return B;
        if(bn == 0) return B;
        if(an <= 1) {
            int[] ret = new int[1];
            ret[0] = A[0] * B[0];
            return ret;
            //return multiply(A, B);
        }
        int half = an / 2;

        int[] A0 = new int[half];
        int[] A1 = new int[an - half];
        
        for(int i = 0; i < half; i++)
            A0[i] = A[i];
        
        for(int i = half; i < an; i++)
            A1[i-half] = A[i];

        if(bn <= half) {
            int[] ret = addTo(karatsuba(A0, B), karatsuba(A1, B), half);
            return ret;
        }

        int[] B0 = new int[half];
        int[] B1 = new int[bn - half];

        for(int i = 0; i < half; i++)
            B0[i] = B[i];

        for(int i = half; i < bn; i++)
            B1[i-half] = B[i];

        int[] Z0 = karatsuba(A0, B0);
        int[] Z1 = karatsuba(A1, B1);

        int[] Ax = addTo(A0, A1, 0);
        int[] Bx = addTo(B0, B1, 0);

        int[] Zx = karatsuba(Ax, Bx);
        Zx = subFrom(Zx, Z0);
        Zx = subFrom(Zx, Z1);

        int[] ret;
        ret = addTo(Z0, Zx, half);
        ret = addTo(ret, Z1, half * 2);

        return ret;
    }

    public static int hugs(String members, String fans) {
        int N = members.length();
        int M = fans.length();
        int[] A = new int[N];
        int[] B = new int[M];
        for(int i = 0; i < N; i++)
            A[i] = (members.charAt(i) == 'M' ? 1 : 0);
        for(int i = 0; i < M; i++)
            B[M-i-1] = (fans.charAt(i) == 'M' ? 1 : 0);

        int[] C = karatsuba(A, B);
        int allHugs = 0;

        for(int i = N-1; i < M; i++) {
            if(C[i] == 0)
                ++allHugs;
        }

        return allHugs;
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        int C = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < C; i++) {
            String members = sc.nextLine();
            String fans = sc.nextLine();
            System.out.println(hugs(members, fans));
        }
    }
}