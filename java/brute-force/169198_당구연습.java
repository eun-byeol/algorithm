import java.util.*;

class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int size = balls.length;
        int[] answer = new int[size];
        for (int i=0; i<size; i++) {
            answer[i] = findMinDist(m, n, startX, startY, balls[i][0], balls[i][1]);
        }
        return answer;
    }
    
    int findMinDist(int m, int n, int sx, int sy, int tx, int ty) {
        int minDist = Integer.MAX_VALUE;
        if (sx != tx) {
            minDist = Math.min(calculate(sx, sy, tx, 2*n-ty), minDist); // 상
            minDist = Math.min(calculate(sx, sy, tx, -1*ty), minDist); // 하
        }
        if (sy != ty) {
            minDist = Math.min(calculate(sx, sy, -1*tx, ty), minDist); // 좌
            minDist = Math.min(calculate(sx, sy, 2*m-tx, ty), minDist); // 우
        }
        if (sx == tx) {
            if (sy < ty) {
                minDist = Math.min(calculate(sx, sy, tx, -1*ty), minDist); // 하
            }
            if (sy > ty) {
                minDist = Math.min(calculate(sx, sy, tx, 2*n-ty), minDist); // 상
            }
        }
        if (sy == ty) {
            if (sx < tx) {
                minDist = Math.min(calculate(sx, sy, -1*tx, ty), minDist); // 좌
            }
            if (sx > tx) {
                minDist = Math.min(calculate(sx, sy, 2*m-tx, ty), minDist); // 우
            }
        }
        return minDist;
    }
    
    int calculate(int x1, int y1, int x2, int y2) {
        return (x1-x2) * (x1-x2) + (y1-y2) * (y1-y2);
    }
}

/*

1. 상하좌우 완탐
1) x좌표 다른 경우 -> 상, 하
2) y좌표 다른 경우 -> 좌, 우
3) x좌표가 같은 경우
  1) sy < ty -> 하
  2) sy > ty -> 상
4) y좌표가 같은 경우
  1) sx < tx -> 좌
  2) sx > tx -> 우

*/
