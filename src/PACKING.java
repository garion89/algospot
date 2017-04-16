import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/*
INPUT
2
6 10
laptop 4 7
camera 2 10
xbox 6 6
grinder 4 7
dumbell 2 5
encyclopedia 10 4
6 17
laptop 4 7
camera 2 10
xbox 6 6
grinder 4 7
dumbell 2 5
encyclopedia 10 4

OUTPUT
24 3
laptop
camera
grinder
30 4
laptop
camera
xbox
grinder
 */

public class PACKING {
    public static int n; // 1 <= n <= 100
    public static int w; // 1 <= w <= 1000
    public static String[] names;
    public static int[] volumes;
    public static int[] urgents;

    public static int[][] cache;

    public static int maxUrgent(int current, int capacity) {
        if(cache[current][capacity] != -1) return cache[current][capacity];

        //base case: out of range
        if(current == n) return 0;
        int switchOff = maxUrgent(current+1, capacity);

        //case1: 현재 위치에 있는 물건을 담을 수 없는 경우
        if(volumes[current] > capacity)
            return cache[current][capacity] = switchOff;

        //case2: 현재 위치에 있는 물건을 담을 수 있는 경우
        int switchOn = urgents[current] + maxUrgent(current+1, capacity-volumes[current]);

        if(switchOff <= switchOn)
            return cache[current][capacity] = switchOn;
        else
            return cache[current][capacity] = switchOff;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int C = sc.nextInt();
        while (C --> 0) {
            n = sc.nextInt();
            w = sc.nextInt();
            names = new String[n];
            volumes = new int[n];
            urgents = new int[n];
            cache = new int[n+1][1001];

            for (int i = 0; i < cache.length; i++)
                Arrays.fill(cache[i], -1);

            for(int i = 0; i < n; i++) {
                names[i] = sc.next();
                volumes[i] = sc.nextInt();
                urgents[i] = sc.nextInt();
            }

            System.out.print(maxUrgent(0, w));

            ArrayList<String> result = new ArrayList<>();

            for(int i = 0; i < n; i++) {
                if(maxUrgent(i, w) != maxUrgent(i+1, w)) {
                    result.add(names[i]);
                    w -= volumes[i];
                }
            }

            int num = result.size();
            System.out.println(" " + num);

            for(int i = 0; i < num; i++)
                System.out.println(result.get(i));
        }
    }
}