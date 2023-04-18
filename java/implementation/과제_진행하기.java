import java.util.*;
class Solution {
    int hoursToMinutes(String time) {
        int minutes = Integer.valueOf(time.substring(3,5));
        minutes += Integer.valueOf(time.substring(0,2)) * 60;
        return minutes;
    }
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        int index = 0;
        Stack<String> names = new Stack<>();
        Stack<Integer> times = new Stack<>();
        Arrays.sort(plans, (o1, o2) -> o1[1].compareTo(o2[1]));
        String[] pre = plans[0];
        for (int i=1; i<plans.length; i++) {
            String[] cur = plans[i];
            int curTime = hoursToMinutes(cur[1]);
            int preTime = hoursToMinutes(pre[1]);
            int remaining = curTime - (preTime + Integer.valueOf(pre[2]));
            if (remaining == 0) {
                answer[index++] = pre[0];
            }
            else if (remaining > 0) {
                answer[index++] = pre[0];
                while (!names.isEmpty()) {
                    String nextName = names.pop();
                    int nextTime = times.pop();
                    if (remaining < nextTime) {
                        names.push(nextName);
                        times.push(nextTime - remaining);
                        break;
                    }
                    answer[index++] = nextName;
                    remaining -= nextTime;
                }
            }
            else {
                names.push(pre[0]);
                times.push(remaining * (-1));
            }
            pre = cur;
        }
        answer[index++] = pre[0];
        while (!names.isEmpty()) {
            answer[index++] = names.pop();
        }
        return answer;
    }
}
