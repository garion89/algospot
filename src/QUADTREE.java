/*
input
4
w
xbwwb
xbwxwbbwb
xxwwwbxwxwbbbwwxxxwwbbbwwwwbb

output
w
xwbbw
xxbwwbbbw
xxwbxwwxbbwwbwbxwbwwxwwwxbbwb
 */
import java.util.Scanner;

public class QUADTREE {
    static char curr;
    static int count;
    static String code;
    static Node root;

    public static Node parser() {
        curr = code.charAt(count);
        count++;

        if(curr == 'x') {
            Node[] children = new Node[4];

            for (int i = 0; i < 4; i++) {
                children[i] = parser();
            }

            return new Node('x', children);
        } else {
            return new Node(curr);
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int C = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < C; i++) {
            count = 0;
            code = sc.nextLine();

            root = parser();
            root.upsideDown();
            root.print();
            System.out.println();
        }
    }
}

class Node {
    //type consists of 'x', 'b', 'w';
    char type;
    //0 means west north, 1 means east north, 2 means west south, 3 means east south
    Node[] children;

    public Node(char type) {
        this.type = type;
    }

    public Node(char type, Node[] children) {
        this.type = type;
        this.children = children;
    }

    void upsideDown() {
        Node temp;

        if(type == 'x') {
            temp = children[0];
            children[0] = children[2];
            children[2] = temp;

            temp = children[1];
            children[1] = children[3];
            children[3] = temp;

            for(int i = 0; i < 4; i++) {
                children[i].upsideDown();
            }
        }
    }

    void print() {
        if(type == 'x') {
            System.out.print(type);
            for(int i = 0; i < 4; i++) {
                children[i].print();
            }
        } else {
            System.out.print(type);
        }
    }
}