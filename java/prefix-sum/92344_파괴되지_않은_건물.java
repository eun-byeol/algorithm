import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int N = board.length;
        int M = board[0].length;
        int[][] map = new int[N+1][M+1];
        
        for (int[] sk : skill) {
            int t = sk[0];
            int r1 = sk[1];
            int c1 = sk[2];
            int r2 = sk[3];
            int c2 = sk[4];
            int d = sk[5];
            
            if (t == 1) d *= (-1); // 공격
            
            map[r1][c1] += d;
            map[r2+1][c2+1] += d;
            map[r2+1][c1] -= d;
            map[r1][c2+1] -= d;
        }
        
        for (int i=0; i<N+1; i++) {
            for (int j=1; j<M+1; j++) {
                map[i][j] += map[i][j-1];
            }
        }
        
        for (int i=0; i<M+1; i++) {
            for (int j=1; j<N+1; j++) {
                map[j][i] += map[j-1][i];
            }
        }
        
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (board[i][j] + map[i][j] > 0) {
                    answer++;
                }
            }
        }
        return answer;
    }
}

/*

** 누적합 문제 **
1. 2차원 누적합을 위한 셋팅
- (r1, c1) -> +d
- (r1+1, c1+1) -> +d
- (r2+1, c1) -> -d
- (r1, c2+1) -> -d

2. 누적계산
- 가로, 세로 순서로 누적 계산

3. 값 비교
- board[i][j] + map[i][j] > 0 확인 -> (+) 하는 것에 유의

*/
