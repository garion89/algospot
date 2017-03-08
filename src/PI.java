/*
INPUT
5
12341234
11111222
12122222
22222222
12673939

OUTPUT
4
2
5
2
14
 */

import java.util.Scanner;

public class PI {

    public static String str;
    public static int[] cache;

    public static int minLevel(int n, int length) {
        int[] nums = new int[length];

        for(int i = 0; i < length; i++)
            nums[i] = Character.getNumericValue(str.charAt(n+i));

        //모든 숫자가 같을 때 (예: 333, 5555) 난이도: 1
        for(int i = 0; i < length; i++) {
            if(i == length - 1) return 1;
            else if(nums[i] != nums[i+1]) break;
        }

        //숫자가 1씩 단조 증가하거나 단조 감소할 때 (예: 23456, 3210) 난이도: 2
        for(int i = 0; i < length; i++) {
            if(i == length - 1) return 2;
            else if(nums[i] - nums[i+1] != 1) break;
        }
        for(int i = 0; i < length; i++) {
            if(i == length - 1) return 2;
            else if(nums[i] - nums[i+1] != -1) break;
        }

        //두 개의 숫자가 번갈아 가며 출현할 때 (예: 323, 54545) 난이도: 4
        for(int i = 0; i < length + 1; i++) {
            if(i == length) return 4;
            if(i % 2 == 0 && nums[0] != nums[i]) break;
            if(i % 2 != 0 && nums[1] != nums[i]) break;
        }
        //숫자가 등차 수열을 이룰 때 (예: 147, 8642) 난이도: 5
        for(int i = 0; i < length - 1; i++) {
            if(i == length - 2) return 5;
            else if(nums[i] - nums[i+1] != nums[i+1] - nums[i+2]) break;
        }

        //그 외의 경우 난이도: 10
        return 10;
    }

    public static int totalMinFrom(int n) {
        if(cache[n] != 0) return cache[n];

        int toEnd = str.length() - n;

        if(toEnd <= 5) return cache[n] = minLevel(n, toEnd);

        int cut3 = minLevel(n, 3) + totalMinFrom(n+3);

        if(toEnd == 6) return cache[n] = cut3;

        int cut4 = minLevel(n, 4) + totalMinFrom(n+4);
        int totalMin = Math.min(cut3, cut4);

        if(toEnd == 7) return cache[n] = totalMin;

        int cut5 = minLevel(n, 5) + totalMinFrom(n+5);
        totalMin = Math.min(totalMin, cut5);

        return cache[n] = totalMin;
    }

    public static void main(String args[]) {

        /* test minLevelFrom
        str = "1234554321333339494914752342";
        //12345
        System.out.println(minLevelFrom(0, 5));
        //54321
        System.out.println(minLevelFrom(5, 5));
        //33333
        System.out.println(minLevelFrom(10, 5));
        //94949
        System.out.println(minLevelFrom(15, 5));
        //147
        System.out.println(minLevelFrom(20, 3));
        //52342
        System.out.println(minLevelFrom(23, 5));
        */

        Scanner sc = new Scanner(System.in);

        int C = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < C; i++) {
            str = sc.nextLine();
            cache = new int[str.length()];
            System.out.println(totalMinFrom(0));
        }
    }
}