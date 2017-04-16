import java.util.*;

/*
INPUT
2
3
2 2 2
2 2 2
3
1 2 3
1 2 1

OUTPUT
8
7
 */

//좀더 쉽고 단순하게 알고리즘을 생각하고 증명하는 훈련이 필요하다
public class LUNCHBOX {
    static class Pair implements Comparable<Pair> {

        private final int eat;
        private final int heat;

        public Pair(int eat, int heat) {
            this.eat = eat;
            this.heat = heat;
        }

        public int getEat() { return eat; }
        public int getHeat() { return heat; }

        @Override
        public int compareTo(Pair o) {
            int result = o.getEat() - this.getEat();

            //이 경우를 고려하지 못해서 틀렸었다. 먹는 시간은 동일한데 데우는 시간이 차이가 나는 경우에는 데우는 시간이 오래걸리는것을 먼저하는것이 최선이다
            if(result == 0) return o.getHeat() - this.getHeat();

            else return result;
        }
    }

    public static int[] heatTimes;
    public static int[] eatTimes;
    public static List<Pair> order;
    public static int n;


    public static int minLunchTime() {
        int ret = 0;
        int begin = 0;

        for(int i = 0; i < n; i++) {
            int eat = order.get(i).getEat();
            begin += order.get(i).getHeat();
            ret = Math.max(ret, begin + eat);
        }

        return ret;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int C = sc.nextInt();

        while (C --> 0) {
            n = sc.nextInt();
            heatTimes = new int[n];
            eatTimes = new int[n];
            order = new ArrayList<>();

            for(int i = 0; i < n; i++)
                heatTimes[i] = sc.nextInt();

            for(int i = 0; i < n; i++)
                eatTimes[i] = sc.nextInt();

            for(int i = 0; i < n; i++)
                order.add(new Pair(eatTimes[i], heatTimes[i]));

            Collections.sort(order);

            System.out.println(minLunchTime());
        }
    }
}