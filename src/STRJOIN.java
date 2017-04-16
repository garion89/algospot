import java.util.*;

/*
INPUT
3
3
2 2 4
5
3 1 3 4 1
8
1 1 1 1 1 1 1 2

OUTPUT
12
26
27
 */

public class STRJOIN {
    public static PriorityQueue<Integer> strLength;
    public static int n;

    public static int minCost() {
        int ret = 0;
        int first;
        int second;
        int sum;

        for(int i = 0; i < n-1; i++) {
            first = strLength.poll();
            second = strLength.poll();
            sum = first + second;
            ret += sum;
            strLength.add(sum);
        }

        return ret;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int C = sc.nextInt();
        while (C --> 0) {
            n = sc.nextInt();
            strLength = new PriorityQueue<>();

            for(int i = 0; i < n; i++)
                strLength.add(sc.nextInt());

            System.out.println(minCost());
        }
    }
}