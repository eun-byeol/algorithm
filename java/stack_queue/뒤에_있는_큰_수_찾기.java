import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        int N = numbers.length;
        int[] answer = new int[N];
        Stack<int[]> stack = new Stack<>();

        for (int i=0; i<N; i++) {
            while (!stack.isEmpty() && stack.peek()[0] < numbers[i]) {
                int[] top = stack.pop();
                answer[top[1]] = numbers[i];
            }
            stack.add(new int[]{numbers[i], i});
        }
        while (!stack.isEmpty()) {
            int[] top = stack.pop();
            answer[top[1]] = -1;
        }
        return answer;
    }
}
