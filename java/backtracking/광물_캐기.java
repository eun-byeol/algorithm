import java.util.*;
class Solution {
    int answer = Integer.MAX_VALUE;
    int N = 0;
    int M = 0;
    int[][] tiredInfo = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
    
    void dfs(int cnt, int tired, int mIdx, int[] picks, int[] minerals) {
        if (cnt >= N || mIdx >= M) {
            answer = Math.min(answer, tired);
            return;
        }

        for (int i=0; i<3; i++) {
            if (picks[i] > 0) {
                int tmpIdx = mIdx;
                int nTired = 0;
                for (int j=0; j<5; j++) {
                    if (mIdx == M) break;
                    nTired += tiredInfo[i][minerals[mIdx++]];
                }
                picks[i]--;
                dfs(cnt+1, tired + nTired, mIdx, picks, minerals);
                picks[i]++;
                mIdx = tmpIdx;
            }
        }
    }
    
    public int solution(int[] picks, String[] minerals) {
        N = Arrays.stream(picks).sum();
        M = minerals.length;
        int[] mineralNums = new int[M];
        
        for (int i=0; i<M; i++) {
            if (minerals[i].equals("diamond")) {
                mineralNums[i] = 0;
            }
            else if (minerals[i].equals("iron")) {
                mineralNums[i] = 1;
            }
            else {
                mineralNums[i] = 2;
            }
        }
        
        dfs(0, 0, 0, picks, mineralNums);
        return answer;
    }
}
