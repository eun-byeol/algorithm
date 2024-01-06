import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int N = enroll.length;
        int[] answer = new int[N+1]; // 1부터 시작
        int[] parent = new int[N+1]; 
        Map<String, Integer> names = new HashMap<>();
        
        int idx = 1; // 1부터 시작
        for (String er : enroll) {
            names.put(er, idx++);
        }
        
        for (int i=0; i<N; i++) {
            int c = names.get(enroll[i]);
            int p = names.getOrDefault(referral[i], 0); // - 인 경우 부모가 center
            parent[c] = p;
        }
        
        int M = seller.length;
        for (int i=0; i<M; i++) {
            int c = names.get(seller[i]);
            int childBenefit = amount[i] * 100;
            while (true) {
                int p = parent[c];
                answer[c] += childBenefit;
                if (c == p) break; // 탈출1. 자기자신인 경우
                int parentBenefit = (int)(childBenefit * 0.1);
                if (parentBenefit < 1) break; //탈출2. 1원 미만인 경우
                answer[c] -= parentBenefit;
                c = p;
                childBenefit = parentBenefit;
            }
        }
        return Arrays.copyOfRange(answer, 1, N+1);
    }
}

/*
전략
1. hashmap<String, Integer> 만들기 -> 이름 인덱스를 상수시간에 접근
2. parent 배열 만들기
	- 모든 인덱스를 1부터 시작함 -> center를 인덱스 0으로 주기 위함
3. 이익 계산하기
	- 종료조건
		1) 부모 == 자식
		2) 부모 이익 < 1

* 디버깅에 시간을 쓴 이유
	- answer에 자식 이익을 더해줄 때, 부모 이익까지 더해서 두번 더해졌었다
*/
