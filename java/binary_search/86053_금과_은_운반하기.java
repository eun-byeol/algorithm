import java.util.*;

class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = -1;
        long start = 0;
        long end = (long) Math.pow(10, 14)*4;
        
        while (start <= end) {
            long mid = (start + end) / 2;
            if (isPossible(mid, a, b, g, s, w, t)) {
                end = mid - 1;
                answer = mid;
                continue;
            }
            start = mid + 1;
        }
        return answer;
    }
    
    boolean isPossible(long totalTime, int a, int b, int[] g, int[] s, int[] w, int[] t) {
        int citySize = g.length;
        long totalGold = 0;
        long totalSilver = 0;
        long totalGoldSilver = 0;
        
        for (int i=0; i<citySize; i++) {
            // 최대 옮길 수 있는 횟수
            long moveCnt = totalTime / (2 * t[i]);
            
            long remainTime = totalTime - moveCnt * 2 * t[i]; // 편도로 갈 수 있는 경우 추가
            if (remainTime >= t[i]) moveCnt++;
            
            // 최대 옮길 수 있는 광물량
            long maxMineral = w[i] * moveCnt;
            
            totalGold += Math.min(g[i], maxMineral);
            totalSilver += Math.min(s[i], maxMineral);
            totalGoldSilver += Math.min(g[i] + s[i], maxMineral);
            
            if (totalGold >= a && totalSilver >= b && totalGoldSilver >= a+b) {
                return true;
            }
        }
        return false;
    }
}

/*

전략
1. time을 축으로 두고 이분탐색 -> 조건이 성립하지 않을 때까지 반복, answer을 계속 갱신(이때 answer는 최소값이 보장된다)
	- 최소 time = 0
	- 최대 time = 4*10^14
		최악의 경우) t[i]=10^5, w[i]=1, g[i]=10^9, s[i]=10^9

2. 조건 검증(= totalTime에 최대로 광물을 옮겼을 때, 가능한 시나리오인가?)
	- 도시 간은 독립시행이므로, 최적의 조합을 찾지 않아도 됨.
	- 포인트는 도시별로 광물 이동의 최대치만 고려하는 것
	- 즉, 도시 배열을 한 번씩 돌면서, 광물 이동의 최대치를 누적해주고 -> 조건 성립시 return

** 조건 **
1) 누적된 총 금 >= a
2) 누적된 총 은 >= b
3) 누적된 총 금+은 >= a+b

이때, 왜 금+은 조건을 고려해야 하나?
-> 해당 time에 금+은 두 조건을 모두 만족하는지 검증하기 위해.
예) a=3, b=2, w=3, 1번만 이동 가능 한 경우일 때
	누적된 총 금=3, 누적된 총 은=2, 누적된 총 금+은=3으로
	금+은 조건이 없다면, 가능한 경우로 판단하게 된다.
    
*/
