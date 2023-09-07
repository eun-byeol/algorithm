import java.util.*;
import java.io.*;

class Solution {
    int answer = 0;
    
    public int solution(int n) {
        dfs(0, new boolean[n], new boolean[n*2-1], new boolean[n*2-1], n);
        return answer;
    }
    
    public void dfs(int row, boolean[] col, boolean[] diaUp, boolean[] diaDown, int N) {
    	if (row == N) {
            answer++;
            return;
        }
    	
    	for (int i=0; i<N; i++) {
    		if (col[i] || diaUp[i+row] || diaDown[N + (row-i) -1]) continue;
    		
    		col[i] = true;
    		diaUp[i+row] = true;
    		diaDown[N + (row-i) -1] = true;
    		
    		dfs(row+1, col, diaUp, diaDown, N);
    		
    		col[i] = false;
    		diaUp[i+row] = false;
    		diaDown[N + (row-i) -1] = false;
    	}
    }
}
