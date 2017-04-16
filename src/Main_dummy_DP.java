import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Arrays;

/*
INPUT


OUTPUT

 */

public class Main_dummy_DP {

    public static int[][] cache;
    public static int n;

    public static void main(String args[]) throws IOException {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);

        int C = Integer.parseInt(br.readLine().trim());

        while (C --> 0) {
            n = Integer.parseInt(br.readLine().trim());
            cache = new int[n+1][n+1];

            for (int i = 0; i < cache.length; i++)
                Arrays.fill(cache[i], -1);

            System.out.println();
        }
    }
}