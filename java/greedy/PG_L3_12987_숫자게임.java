import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);
        
        int i=0;
        int j=0;
        int N = A.length;
        while(i < N && j < N) {
            if (A[i] < B[j]) {
                answer++;
                i++;
                j++;
            }
            else {
                j++;
            }
        }
        return answer;
    }
}

/*

그리디
1. A 내림차순 정렬
2. B 오름차순 정렬
3. 
- A < B -> a++, b++, score++
- A >= B -> b++

*/
