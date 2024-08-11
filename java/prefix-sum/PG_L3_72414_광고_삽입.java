import java.util.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int N = toSeconds(play_time) + 1;
        int[] totalTime = new int[N];
        
        for (String log : logs) { // 누적 구간 합 구하기
            int s = toSeconds(log.substring(0,8));
            int e = toSeconds(log.substring(9,17));
            totalTime[s] += 1;
            totalTime[e] -= 1;
        }
        
        for (int i=0; i<N-1; i++) {
            totalTime[i+1] += totalTime[i];
        }
        
        int advS = toSeconds(adv_time); // 최적 시간 찾기
        int startS = 0;
        long max = 0;
        
        for (int i=0; i<advS; i++) {
            max += totalTime[i];
        }
        
        long preT = max;
        
        for (int i=1; i<N-advS+1; i++) {
            long curT = preT + totalTime[i + advS - 1];
            if (preT > 0) {
                curT -= totalTime[i-1];
            }
            if (curT > max) {
                max = curT;
                startS = i;
            }
            preT = curT;
        }

        return toTime(startS);
    }
    
    int toSeconds(String time) {
        char[] t = time.toCharArray();
        int h = (t[0] - '0') * 10 + t[1] - '0';
        int m = (t[3] - '0') * 10 + t[4] - '0';
        int s = (t[6] - '0') * 10 + t[7] - '0';
        return h * 3600 + m * 60 + s;
    }
    
    String toTime(int s) {
        int h = s / 3600;
        s %= 3600;
        int m = s / 60;
        s %= 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}
/*

1. 초단위로 시간 기준 변경
2. 누적 구간 합 구하기
3. 최적 시간 찾기


누적 재생시간이 가장 큰 구간을 고르라
시작 시각을 반환
우선순위 : 가장 빠른 시작 시간

*/
