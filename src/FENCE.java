/*
INPUT
5
7
7 1 5 9 6 7 3
7
1 4 4 4 4 1 1
4
1 8 2 2
7
1 2 3 4 5 6 7
7
7 6 5 4 3 2 1

OUTPUT
20
16
8
16
16
 */

import java.util.Scanner;

public class FENCE {

    public static int[] boardHeight;
    public static int N;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int C = sc.nextInt();

        for(int i = 0; i < C; i++) {
            N = sc.nextInt();

            boardHeight = new int[N];

            for(int j = 0; j < N; j++) {
                boardHeight[j] = sc.nextInt();
            }

            System.out.println(solve(0, N-1));
        }
    }

    public static int goRight(int end, int right, int height) {
        while(right < end) {
            if(height <= boardHeight[right+1])
                right++;
            else
                break;
        }

        return right;
    }

    public static int goLeft(int start, int left, int height) {
        while(start < left) {
            if(height <= boardHeight[left-1])
                left--;
            else
                break;
        }

        return left;
    }

    public static int solve(int start, int end) {

        //base case: just one board
        if(start == end) return boardHeight[start];

        int left = (start + end) / 2;
        int right = left + 1;

        int ret = Math.max(solve(start, left), solve(right, end));

        int height = Math.min(boardHeight[left], boardHeight[right]);
        int nextHeight;

        ret = Math.max(ret, height * 2);

        while(start < left || right < end) {


            if(start == left) {
                nextHeight = boardHeight[right+1];
            } else if(end == right) {
                nextHeight = boardHeight[left-1];
            } else {
                nextHeight = Math.max(boardHeight[left-1], boardHeight[right+1]);
            }

            //height는 그 이전 height보다 작아야 된다는 조건을 위배하였기 때문에 계속 오답이 나왔다.
            height = height < nextHeight ? height : nextHeight;

            right = goRight(end, right, height);
            left = goLeft(start, left, height);

            ret = Math.max(ret, height * (right - left + 1));
        }

        return ret;
    }
}

/* 교과서 답안
public class FENCE {

    public static int[] boardHeight;
    public static int N;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int C = sc.nextInt();

        for(int i = 0; i < C; i++) {
            N = sc.nextInt();

            boardHeight = new int[N];

            for(int j = 0; j < N; j++) {
                boardHeight[j] = sc.nextInt();
            }

            System.out.println(solve(0, N-1));
        }
    }

    public static int solve(int start, int end) {

        //base case: just one board
        if(start == end) return boardHeight[start];

        int left = (start + end) / 2;
        int right = left + 1;

        int ret = Math.max(solve(start, left), solve(right, end));

        int height = Math.min(boardHeight[left], boardHeight[right]);

        ret = Math.max(ret, height * 2);

        while(start < left || right < end) {
            if(start == left) {
                right++;
                height = Math.min(height, boardHeight[right]);
            } else if(right == end) {
                left--;
                height = Math.min(height, boardHeight[left]);
            } else if (boardHeight[left - 1] < boardHeight[right + 1]) {
                right++;
                height = Math.min(height, boardHeight[right]);
            } else {
                left--;
                height = Math.min(height, boardHeight[left]);
            }

            ret = Math.max(ret, height * (right - left + 1));
        }

        return ret;
    }
}
*/

/* 잘 못 푼 답안
public class FENCE {

    public static int[] boardHeight;
    public static int N;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int C = sc.nextInt();

        for(int i = 0; i < C; i++) {
            N = sc.nextInt();

            boardHeight = new int[N];

            for(int j = 0; j < N; j++) {
                boardHeight[j] = sc.nextInt();
            }

            System.out.println(solve(0, N));
        }
    }

    public static int solve(int start, int end) {

        int mid = (start + end) / 2;

        //base case
        if(start == mid) {
            return boardHeight[start];
        } else {
            int centerMax = centerSolve(start, end);
            int leftMax = solve(start, mid);
            int rightMax = solve(mid, end);
            return Math.max(Math.max(leftMax, rightMax), centerMax);
        }
    }

    //boardHeight mid-1번째 판자와 mid번째 판자를 포함하는 것중 최대 넓이를 return하는 함수
    public static int centerSolve(int start, int end) {

        int mid = (start + end) / 2;

        int leftCursor = mid - 1;
        int rightCursor = mid;

        int currArea;
        int currHeight = boardHeight[leftCursor] <= boardHeight[rightCursor] ? boardHeight[leftCursor] : boardHeight[rightCursor];
        int maxArea = currHeight * 2;
        
        int leftest;
        int rightest;

        while(start <= leftCursor || rightCursor < end) {
            if(leftCursor < start)
                currHeight = boardHeight[rightCursor];
            else if(rightCursor >= end)
                currHeight = boardHeight[leftCursor];
            else
                currHeight = boardHeight[leftCursor] <= boardHeight[rightCursor] ? boardHeight[leftCursor] : boardHeight[rightCursor];

            while(true) {
                if(leftCursor < start) {
                    leftest = start;
                    break;
                } else if(boardHeight[leftCursor] < currHeight) {
                    leftest = leftCursor + 1;
                    break;
                } else {
                    leftCursor--;
                }
            }

            while(true) {
                if(end <= rightCursor) {
                    rightest = end - 1;
                    break;
                } else if(boardHeight[rightCursor] < currHeight) {
                    rightest = rightCursor - 1;
                    break;
                } else {
                    rightCursor++;
                }
            }

            currArea = currHeight * (rightest - leftest + 1);

            maxArea = maxArea > currArea ? maxArea : currArea;
        }

        return maxArea;
    }
}
*/