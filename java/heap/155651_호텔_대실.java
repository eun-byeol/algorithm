import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Arrays.sort(book_time, (o1, o2) -> o1[0].compareTo(o2[0]));
        
        for (String[] times : book_time) {
            int startM = timeToMinute(times[0]);
            int endM = timeToMinute(times[1]) + 10; // 청소 시간 추가
            if (!pq.isEmpty() && pq.peek() <= startM) { // 객실 추가X
                pq.remove();
                pq.add(endM);
                continue;
            }
            // 객실 추가
            answer++;
            pq.add(endM);
        }
        return answer;
    }
    
    int timeToMinute(String time) {
        return Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));
    }
}

/*

1. book_time 시작 시간 기준으로 오름차순 정렬
2. 우선순위 큐 활용
    - 객실 사용 가능 시간(= 종료 시간 + 10분)
3. 객실을 추가하는 경우
    - peek > startM -> cnt++, 큐에 add
4. 객실을 추가하지 않아도 되는 경우
    - peek <= startM -> remove, add
5. 문자열 처리
    - 시간 -> 분 환산

*/
