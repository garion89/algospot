/*
INPUT
3
2 1
0 1
4 6
0 1 1 2 2 3 3 0 0 2 1 3
6 10
0 1 0 2 1 2 1 3 1 4 2 3 2 4 3 4 3 5 4 5

OUTPUT
1
3
4
 */

import java.util.Scanner;

public class PICNIC {

    static boolean[][] friendRelation = new boolean[10][10];

    static int countMix(boolean[] occupied, int left, int right, int n) {

        for(int i = 0; i < n; i++) {
            if(occupied[i] == false) {
                break;
            } else if(i == n - 1) {
                return 1;
            }
        }

        if(n <= left) {
            return 0;
        } else if(occupied[left] || n <= right) {
            return countMix(occupied, left + 1, left + 2, n);
        } else {
            if(friendRelation[left][right]) {
                if(occupied[right])
                    return countMix(occupied, left, right+1, n);
                else {
                    int before = countMix(occupied, left, right + 1, n);
                    occupied[left] = occupied[right] = true;
                    int after = countMix(occupied, left + 1, left + 2, n);
                    occupied[left] = occupied[right] = false;
                    return before + after;
                }
            } else {
                return countMix(occupied, left, right + 1, n);
            }
        }
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        int numCase = sc.nextInt();
        boolean[] occupied = new boolean[10];

        for(int i = 0; i < numCase; i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            friendRelation = new boolean[10][10];

            for (int j = 0; j < m; j++) {
                int a = sc.nextInt();
                int b = sc.nextInt();

                friendRelation[a][b] = friendRelation[b][a] = true;
            }

            System.out.println(countMix(occupied, 0, 1, n));
        }

        sc.close();

    }
}
