import java.util.*;
class Solution {
    int calculate(int x1, int y1, int x2, int y2) {
        return (x2-x1) * (x2-x1) + (y2-y1) * (y2-y1);
    }
    int findMinDist(int[] ball, int x, int y, int m, int n) {
        int minDist = Integer.MAX_VALUE;
        int a = ball[0];
        int b = ball[1];
        int a1, b1;
        
        if (x != a) {
            a1 = a;
            b1 = 2 * n - b;
            minDist = Math.min(calculate(x, y, a1, b1), minDist);
            
            a1 = a;
            b1 = b * (-1);
            minDist = Math.min(calculate(x, y, a1, b1), minDist);
        }
        if (y != b) {
            a1 = 2 * m - a;
            b1 = b;
            minDist = Math.min(calculate(x, y, a1, b1), minDist);
            
            a1 = a * (-1);
            b1 = b;
            minDist = Math.min(calculate(x, y, a1, b1), minDist);
        }
        if (y == b) {
            if (x < a) {
                minDist = Math.min((x+a) * (x+a), minDist);
            }
            else {
                minDist = Math.min((2*m-a-x) * (2*m-a-x), minDist);
            }
        }
        if (x == a) {
            if (y < b) {
                minDist = Math.min((b+y) * (b+y), minDist);
            }
            else {
                minDist = Math.min((2*n-b-y) * (2*n-b-y), minDist);
            }
        }
        return minDist;
    }
    
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int N = balls.length;
        int[] answer = new int[N];
        
        for (int i=0; i<N; i++) {
            answer[i] = findMinDist(balls[i], startX, startY, m, n);
        }
        return answer;
    }
}
