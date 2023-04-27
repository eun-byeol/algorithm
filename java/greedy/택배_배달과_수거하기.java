import java.util.*;
class Solution {
    Stack<Integer> initStack(int[] data, int n) {
        Stack<Integer> st = new Stack<>();
        for (int i=0; i<n; i++) {
            for (int j=0; j<data[i]; j++) {
                st.push(i+1);
            }
        }
        return st;
    }
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        Stack<Integer> stD = initStack(deliveries, n);
        Stack<Integer> stP = initStack(pickups, n);
        
        while (!stD.isEmpty() && !stP.isEmpty()) {
            int topD = stD.peek();
            int topP = stP.peek();
            answer += Math.max(topD, topP) * 2;
            for (int i=0; i<cap; i++) {
                if (!stD.isEmpty()) {
                    stD.pop();
                }
                if (!stP.isEmpty()) {
                    stP.pop();
                }
            }
        }
        
        while (!stD.isEmpty()) {
            int topD = stD.peek();
            answer += topD * 2;
            for (int i=0; i<cap; i++) {
                if (!stD.isEmpty()) {
                    stD.pop();
                }
            }
        }
        
        while (!stP.isEmpty()) {
            int topP = stP.peek();
            answer += topP * 2;
            for (int i=0; i<cap; i++) {
                if (!stP.isEmpty()) {
                    stP.pop();
                }
            }
        }
        return answer;
    }
}
