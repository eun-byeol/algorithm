import java.util.*;

class Solution {
    String answer = "";
    public String solution(String p) {
        recursive("", p);
        return answer;
    }
    
    void recursive(String u, String v) {
        if (v.length() == 0) { // 1
            return;
        }
        int lastIdx = findBalancedIdx(v);
        u = v.substring(0, lastIdx+1);
        v = v.substring(lastIdx+1); // 2
        
        if (isCorrect(u)) { // 3
            answer += u;
            recursive(u, v);
            return;
        }
        answer += "("; // 4
        recursive(u, v);
        answer += ")";
        attach(u.toCharArray());
    }
    
    int findBalancedIdx(String str) {
        int idx = 0;
        int openCnt = 0;
        int closeCnt = 0;
        
        for (char c : str.toCharArray()) {
            if (c == '(') {
                openCnt++;
            }
            if (c == ')') {
                closeCnt++;
            }
            if (openCnt == closeCnt) {
                return idx;
            }
            idx++;
        }
        return idx;
    }
    
    boolean isCorrect(String str) {
        int openCnt = 0;
        
        for (char c : str.toCharArray()) {
            if (c == '(') {
                openCnt++;
            }
            if (c == ')') {
                openCnt--;
                if (openCnt < 0) {
                    return false;
                }
            }
        }
        return openCnt == 0;
    }
    
    void attach(char[] u) {
        for (int i=1; i<u.length-1; i++) {
            if (u[i] == '(') {
                answer += ')';
            }
            if (u[i] == ')') {
                answer += '(';
            }
        }
    }
}
