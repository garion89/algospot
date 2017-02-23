/*
input
3
3 7
#.....#
#.....#
##...##
3 7
#.....#
#.....#
##..###
8 10
##########
#........#
#........#
#........#
#........#
#........#
#........#
##########

output
0
2
1514
 */

import java.util.Scanner;

public class BOARDCOVER {
    static int H, W;
    static boolean[][] isEmptyBoard;
    static final int[][][] coverType = {
        { {0, 0}, {1, 0}, {0, 1} },
        { {0, 0}, {0, 1}, {1, 1} },
        { {0, 0}, {1, 0}, {1, 1} },
        { {0, 0}, {1, 0}, {1, -1} }
    };

    public static boolean check(int y, int x, int type) {
        boolean block1 = isEmptyBoard[y + coverType[type][1][0]][x + coverType[type][1][1]];
        boolean block2 = isEmptyBoard[y + coverType[type][2][0]][x + coverType[type][2][1]];

        return block1 && block2;
    }

    public static void set(int y, int x, int type, boolean value) {
        int ny, nx;

        for(int block = 0; block < 3; block++) {
            ny = y + coverType[type][block][0];
            nx = x + coverType[type][block][1];
            isEmptyBoard[ny][nx] = value;
        }
    }

    public static int solve() {
        //종료조건은 보드의 모든 블락이 empty가 아닌 경우이다.
        boolean full = true;
        int y = -1;
        int x = -1;

        for(int i = 1; i <= H; i++) {
            for(int j = 1; j <= W; j++) {
                if(isEmptyBoard[i][j]) {
                    y = i;
                    x = j;
                    break;
                }
            }

            if(y > -1) break;
        }

        if(y == -1) return 1;

        int ret = 0;

        for(int type = 0; type < 4; type++) {
            if(check(y, x, type)) {
                set(y, x, type, false);
                ret += solve();
                set(y, x, type, true);
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int C = scan.nextInt();

        for(int c = 0; c < C; c++) {
            H = scan.nextInt();
            W = scan.nextInt();
            isEmptyBoard = new boolean[H + 2][W + 2];

            //using trick not to care about boundary cases
            for(int i = 1; i < H + 1; i++) {
                String line = scan.next();
                for(int j = 0; j < W; j++) {
                    //true means this sector is not occupied by black block
                    if(line.charAt(j) == '.')
                        isEmptyBoard[i][j+1] = true;
                    else {
                        isEmptyBoard[i][j+1] = false;
                    }
                }
            }

            for(int i = 0; i < H + 2; i++) {
                isEmptyBoard[i][0] = false;
                isEmptyBoard[i][W + 1] = false;
            }

            for(int i = 0; i < W + 2; i++) {
                isEmptyBoard[0][i] = false;
                isEmptyBoard[H+1][i] = false;
            }

/* degugging용으로 필요했던 코드. 이런걸로 내 생각이 제대로 작동하는지를 확인할 필요가 있다.
            for(int i = 0; i < H + 2; i++) {
                for(int j = 0; j < W + 2; j++) {
                    if(isEmptyBoard[i][j])
                        System.out.print(".");
                    else
                        System.out.print("#");
                }
                System.out.println("");
            }
*/
            System.out.println(solve());
        }

        scan.close();
    }

}
