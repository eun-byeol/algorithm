import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        int N = a.length;
        int[] cnt = new int[N];
        
        for (int num : a) {
            cnt[num]++;
        }
        
        for (int union=0; union<N; union++) {
            if (answer >= cnt[union]) continue; // answer보다 작으면 건너뛰기
            int starSize = 0;
            for (int i=0; i<N-1; i++) {
                if (a[i] != union && a[i+1] != union) continue;
                if (a[i] == a[i+1]) continue;
                starSize++;
                i++;
            }
            answer = Math.max(starSize, answer);
        }
        return answer * 2;
    }
}

/*
- 효율성 문제
- 재귀로 풀면(모든 부분집합 구하기 -> 검증) 시간초과!
- 조건 검증 후 -> 부분집합 생성 가능한지 확인하여 해결
*/
