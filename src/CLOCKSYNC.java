/*
input
2
12 6 6 6 6 6 12 12 12 12 12 12 12 12 12 12
12 9 3 12 6 6 9 3 12 9 12 9 12 12 6 6

output
2
9
 */

//TODO: 이 코드를 변형시켜서 for문을 재귀로 대체해보자. 그리고 나서 속도를 비교해보자. 파일 이름은 CLOCKSYNC2로 해서!

import java.util.Scanner;

public class CLOCKSYNC {
    static final int MAX = 987654321;
    public static int[] clickNum;

    static final int[][] switchConnected = {
            { 0, 1, 2},
            { 3, 7, 9, 11},
            { 4, 10, 14, 15 },
            { 0, 4, 5, 6, 7 },
            { 6, 7, 8, 10, 12 },
            { 0, 2, 14, 15 },
            { 3, 14, 15 },
            { 4, 5, 7, 14, 15 },
            { 1, 2, 3, 4, 5 },
            { 3, 4, 5, 9, 13 }
    };

    static final int[] clocks = new int[16];

    public static void click() {

        for(int i = 0; i < 10; i++) {
            int len = switchConnected[i].length;
            for(int j = 0; j < len; j++) {
                clocks[switchConnected[i][j]] += clickNum[i];
            }
        }

        for(int i = 0; i < 16; i++) {
            clocks[i] %= 4;
        }
    }

    public static void unclick() {

        for(int i = 0; i < 10; i++) {
            int len = switchConnected[i].length;
            for(int j = 0; j < len; j++) {
                clocks[switchConnected[i][j]] -= clickNum[i];
            }
        }
        for(int i = 0; i < 16; i++) {
            clocks[i] %= 4;
            if(clocks[i] == 4) clocks[i] = 0;
        }
    }

    public static int sum() {
        int ret = 0;

        for(int i = 0; i < 10; i++) {
            ret += clickNum[i];
        }

        return ret;
    }

    public static boolean isAllZero() {
        for(int i = 0; i < 16; i++) {
            if(clocks[i] != 0) return false;
        }
        return true;
    }

    public static int[] makeQuadTen(int num) {
        int[] quadArr = new int[10];

        for(int i = 0; i < 10; i++) {
            quadArr[i] = num % 4;
            num /= 4;
        }

        return quadArr;
    }

    public static int solve() {
        int num  = (int) Math.pow(4, 10);
        int ret = 987654321;
        int total = -1;

        for(int i = 0; i < num; i++) {

            clickNum = makeQuadTen(i);

            /* test makeQuadTen
            if(i < 100) {
                System.out.print(i + " : ");
                for(int j = 0; j < 10; j++) {
                    System.out.print(clickNum[j] + " ");
                }
                System.out.println();
            }
            */

            click();
            if(isAllZero()) total = sum();
            unclick();

            if(total >= 0)
                ret = Math.min(ret, total);
        }

        return ret;
    }
    public static void main(String args[]) {


        /* test switchConnected correct
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < switchConnected[i].length; j++) {
                System.out.print(switchConnected[i][j] + " ");
            }
            System.out.println();
        }
        */

        /*  test isAllZero
        for(int i = 0; i < 16; i++)
            System.out.print(arr[i] + " ");
        System.out.println(isAllZero());
        */

        /* test sum
        int[] test = {1, 2, 3, 4};
        System.out.println(sum(test));
        */

        Scanner sc = new Scanner(System.in);

        int C = sc.nextInt();
        int temp;
        int result;

        for(int i = 0; i < C; i++) {
            for(int j = 0; j < 16; j++) {
                temp = sc.nextInt();
                temp /= 3;
                if(temp == 4) temp = 0;
                clocks[j] = temp;
            }


            //long startTime = System.currentTimeMillis();

            result = solve();

            //long endTime = System.currentTimeMillis();

            /* time check
            long lTime = endTime - startTime;

            System.out.println("TIME : " + lTime + "(ms)");
            */

            System.out.println(result != MAX ? result : "-1");
        }
    }
}
