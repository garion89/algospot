import javafx.beans.NamedArg;
import javafx.util.Pair;
import java.util.*;

/*
INPUT
3
9 2
1 9 7 4 2 6 3 11 10
8 4
2 1 4 3 6 5 8 7
8 2
5 6 7 8 1 2 3 4

OUTPUT
4
1 2 3 11
4
1 3 6 8
4
5 6 7 8
 */

public class KLIS {
    static class IntegerPair extends Pair<Integer, Integer> implements Comparable<Pair<Integer, Integer>>{
        /**
         * Creates a new pair
         *
         * @param key   The key for this pair
         * @param value The value to use for this pair
         */
        public IntegerPair(@NamedArg("key") Integer key, @NamedArg("value") Integer value) {
            super(key, value);
        }

        @Override
        public int compareTo(Pair<Integer, Integer> o) {
            return this.getKey() - o.getKey();
        }
    }

    public static int n, k;
    public static int[] cacheLen, cacheCnt, S;
    public static final int MAX = Integer.MAX_VALUE;

    //시작위치를 받아서 시작위치부터 가장 긴 부분 수열의 길이를 구하는 함수
    //시작위치가 -1이라면 0 ~ 끝까지 모두 확인
    public static int lis(int start) {
        int ret = cacheLen[start + 1];
        if(ret != -1) return ret;
        ret = 1;

        for(int next = start + 1; next < n; next++)
            if(start == -1 || S[start] < S[next])
                ret = Math.max(ret, lis(next) + 1);

        return cacheLen[start+1] = ret;
    }

    //start에서 시작하는 가장 긴 부분 수열의 갯수를 구하는 함수
    //lis(start) == 1 이라면 start에서 시작하는 가장 긴 부분 수열의 길이가 1이라는 뜻
    public static int count(int start) {
        if(lis(start) == 1) return 1;

        int ret = cacheCnt[start + 1];
        if(ret != -1) return ret;
        ret = 0;

        for(int next = start + 1; next < n; next++)
            if((start == -1 || S[start] < S[next])
                    && lis(start) == lis(next) + 1)
                //이 부분에서 long으로 형변환을 해야되는 이유를 생각해볼 필요가 있다.
                //overflow를 처리함에 있어서 ret + count(next)가 MAX값보다 커질 가능성이 있는 수이다.
                //그런데 이 수를 int로 정해놓으면 overflow에 의해서 MAX보다 작아지게 되어 MAX 대신 그 값을 취하게 되어 오류가 발생할 수 있다.
                ret = (int) Math.min((long) MAX, (long) ret + count(next));

        return cacheCnt[start + 1] = ret;
    }

    //start에서 시작하는 LIS 중 사전순으로 skip개 건너뛴 수열을 lis에 저장한다
    public static void reconstruct(int start, int skip, List<Integer> lis) {
        if(start != -1) lis.add(S[start]);
        List<IntegerPair> followers= new ArrayList<>();

        //followrs에 현재 위치에서 가장 긴 부분 수열을 되게 만드는 그 다음 수와 그 숫자가 있는 위치를 저장한다.
        for(int next = start+1; next < n; next++)
            if((start == -1 || S[start] < S[next]) &&
                    lis(start) == lis(next) + 1)
                followers.add(new IntegerPair(S[next], next));

        Collections.sort(followers);

        for(int i = 0; i < followers.size(); i++) {
            int idx = followers.get(i).getValue();
            int cnt = count(idx);
            if(cnt <= skip)
                skip -= cnt;
            else {
                reconstruct(idx, skip, lis);
                break;
            }
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int C = sc.nextInt();
        while (C --> 0) {
            n = sc.nextInt();
            k = sc.nextInt();

            cacheLen = new int[n+1];
            cacheCnt = new int[n+1];
            S = new int[n];

            for(int i = 0; i < n+1; i++)
                cacheLen[i] = -1;
            for(int i = 0; i < n+1; i++)
                cacheCnt[i] = -1;
            for(int i = 0; i < n; i++)
                S[i] = -1;

            for(int i = 0; i < n; i++)
                S[i] = sc.nextInt();

            List<Integer> lis = new ArrayList<>();

            reconstruct(-1, k - 1, lis);

            System.out.println(lis.size());

            for(int i = 0; i < lis.size(); i++)
                System.out.print(lis.get(i) + " ");

            System.out.println();
        }
    }
}