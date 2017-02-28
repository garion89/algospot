/*
INPUT
2
he?p
3
help
heap
helpp
*p*
3
help
papa
hello

OUTPUT
heap
help
help
papa
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WILDCARD {
    public static String W;
    public static int wl;
    public static String S;
    public static int sl;
    public static int[][] cache;

    public static int match(int wp, int sp) {

        if(cache[wp][sp] != -1)
            return cache[wp][sp];

        //base case1: wp가 끝까지 갔다면 sl과 sp가 같다면 true, 다르면 false
        if(wl == wp) return (sl == sp) ? (cache[wp][sp] = 1) : (cache[wp][sp] = 0);

        //base case2: sp가 끝까지 갔다면, 현재 W가 *가 아니면 무조건 false
        char wc = W.charAt(wp);
        if(sl == sp) {
            if(wc == '*') return match(wp+1, sp);
            else return cache[wp][sp] = 0;
        }

        char sc = S.charAt(sp);

        if(wc == '*') {
            return match(wp+1, sp) + match(wp, sp+1);
        } else if(wc == '?' || wc == sc){
            return match(wp+1, sp+1);
        }

        return cache[wp][sp] = 0;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int C = sc.nextInt();
        int N;
        sc.nextLine();

        ArrayList<String> result;

        for(int i = 0; i < C; i++) {
            result = new ArrayList<>();
            W = sc.nextLine();
            wl = W.length();
            N = sc.nextInt();
            sc.nextLine();

            for(int j = 0; j < N; j++) {
                S = sc.nextLine();
                sl = S.length();

                cache = new int[wl+1][sl+1];

                //cache initialization
                for(int a = 0; a <= wl; a++) {
                    for(int b = 0; b <= sl; b++) {
                        cache[a][b] = -1;
                    }
                }

                if (match(0,0) != 0)
                    result.add(S);
            }

            Collections.sort(result);

            for(int j = 0; j < result.size(); j++) {
                System.out.println(result.get(j));
            }
        }
    }
}