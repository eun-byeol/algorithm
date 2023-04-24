import java.util.*;
class Solution {
    int changeToMinute(String time) {
        return Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));
    }

    public int solution(String[][] book_time) {
        int answer = 0;
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        Arrays.sort(book_time, (s1, s2) -> s1[0].compareTo(s2[0]));

        for (String[] time : book_time) {
            int start = changeToMinute(time[0]);
            int end = changeToMinute(time[1]);
            if (minQ.peek() == null || minQ.peek() + 10 > start) {
                minQ.add(end);
                answer++;
                continue;
            }
            minQ.poll();
            minQ.add(end);
        } 
        return answer;
    }
}
