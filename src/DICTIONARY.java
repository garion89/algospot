import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
INPUT
3
3
ba
aa
ab
5
gg
kia
lotte
lg
hanhwa
6
dictionary
english
is
ordered
ordinary
this

OUTPUT
INVALID HYPOTHESIS
ogklhabcdefijmnpqrstuvwxyz
abcdefghijklmnopqrstuvwxyz
 */

public class DICTIONARY {
    public static boolean[][] graph;
    public static boolean[] seen;
    public static ArrayList<Integer> order;

    public static void dfs(int here) {
        seen[here] = true;

        for(int there = 0; there < 26; there++)
            if(graph[here][there] && !seen[there])
                dfs(there);

        order.add(here);
    }

    public static void addEdge(String curr, String next) {
        int len = Math.min(curr.length(), next.length());
        char c;
        char n;

        for(int i = 0; i < len; i++) {
            c = curr.charAt(i);
            n = next.charAt(i);
            if(c != n) {
                graph[c - 'a'][n - 'a'] = true;
                break;
            }
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int C = sc.nextInt();
        int n;

        String curr;
        String next;

        while (C --> 0) {
            n = sc.nextInt();
            graph = new boolean[26][26];
            curr = sc.next();

            for(int i = 0; i < n-1; i++) {
                next = sc.next();
                addEdge(curr, next);
                curr = next;
            }

            seen = new boolean[26];
            order = new ArrayList<>();

            for(int i = 0; i < 26; i++)
                seen[i] = false;

            for(int i = 0; i < 26; i++)
                if(!seen[i]) dfs(i);

            boolean valid = true;

            for(int i = 0; i < 26; i++)
                for(int j = i+1; j < 26; j++)
                    if(graph[order.get(i)][order.get(j)])
                        valid = false;

            if(valid) {
                for (int i = 0; i < 26; i++)
                    System.out.print((char) (order.get(25 - i) + 'a'));
                System.out.println();
            } else {
                System.out.println("INVALID HYPOTHESIS");
            }
        }
    }
}