import java.util.*;
import java.io.*;

class Solution {
    int answer = 0;
    
    public int solution(int n) {
        dfs(0, new boolean[n], new boolean[n*2-1], new boolean[n*2-1], n);
        return answer;
    }
    
    public void dfs(int depth, boolean[] col, boolean[] diaUp, boolean[] diaDown, int N) {
    	if (depth == N) {
            answer++;
            return;
        }
        
        for (int i=depth; i<N; i++) {
            for (int j=0; j<N; j++) {            	
                if (col[j] || diaUp[i+j] || diaDown[N+(i-j)-1]) continue;
                col[j] = true;
                diaUp[i+j] = true;
                diaDown[N+(i-j)-1] = true;
                
                dfs(depth+1, col, diaUp, diaDown, N);
                
                col[j] = false;
                diaUp[i+j] = false;
                diaDown[N+(i-j)-1] = false;
            }
            return;
        }
    }
}
