import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        LinkedList<String> nums = new LinkedList<>();
        
        while (n >= k) {
            nums.addFirst(String.valueOf(n % k));
            n /= k;
        }
        nums.addFirst(String.valueOf(n));
        
        String numStr = String.join("", nums);
        
        StringTokenizer st = new StringTokenizer(numStr, "0");
        
        while (st.hasMoreTokens()) {
            if (isPrime(st.nextToken())) {
                answer++;
            }
        }
        
        return answer;
    }
    
    boolean isPrime(String num) {
        double v = Double.parseDouble(num);
        if (v == 1) {
            return false;
        }
        int m = (int) Math.sqrt(v);
        for (int i=2; i<=m; i++) {
            if (v % i == 0) {
                return false;
            }
        }
        return true;
    }
}

/*

1. k 진수 변환
2. 0 기준 분할
3. 소수 판별

*/
