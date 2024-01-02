import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {    
        int[][] dp = new int[301][301];
        int maxA = alp;
        int maxC = cop;
        
        for (int[] problem : problems) { // 충족해야 하는 최대 알고력, 코딩력
            maxA = Math.max(problem[0], maxA);
            maxC = Math.max(problem[1], maxC);
        }
        
        for (int i=alp; i<maxA+1; i++) {
            for (int j=cop; j<maxC+1; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[alp][cop] = 0;
        
        for (int i=alp; i<maxA+1; i++) {
            for (int j=cop; j<maxC+1; j++) {
                if (i < maxA) {
                    dp[i+1][j] = Math.min((dp[i][j] + 1), dp[i+1][j]);
                }
                if (j < maxC)  {
                    dp[i][j+1] = Math.min((dp[i][j] + 1), dp[i][j+1]);
                }
                
                for (int[] problem : problems) {
                    if (i < problem[0] || j < problem[1]) continue;
                    int nxtA = Math.min(i+problem[2], maxA);
                    int nxtC = Math.min(j+problem[3], maxC);
                    dp[nxtA][nxtC] = Math.min(dp[nxtA][nxtC], dp[i][j] + problem[4]);
                }
            }
        }
        
        return dp[maxA][maxC];
    }
}

/*
* DP 문제 *
1. 구해야 하는 타겟 i, j?
    - maxAlp, maxCop 구하기
2. 최대 값으로 초기화
    - dp[alp][cop] = 0 유의
3. 개선
    1. 능력 키우기
        - 알고력 : dp[i+1][j] = min(dp[i][j]+1, dp[i+1][j])
        - 코딩력 : dp[i][j+1] = min(dp[i][j]+1, dp[i][j+1])
    2. 문제풀기
        - dp[i+rwA][j+rwC] = min(dp[i][j]+cost, dp[i+rwA][j+rwC])
        - i < rqA or j < rqC 이면 문제를 못 품 유의
        
* 핵심 포인트 *
- i+rwA, j+rwC의 최대값이 maxA, maxC를 넘게 하지 않는다!!

* 디버깅 포인트 *
maxA, maxC 를 0으로 초기화하면 왜 일부 테케 실패가 뜨는가?
- alp > maxA, cop > maxC 케이스
*/
