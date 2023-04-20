import java.util.*;
class Solution {
    List<int[]> bingos = new ArrayList<>() {{
        add(new int[]{1,1,1,0,0,0,0,0,0});
        add(new int[]{0,0,0,1,1,1,0,0,0});
        add(new int[]{0,0,0,0,0,0,1,1,1});
        add(new int[]{1,0,0,1,0,0,1,0,0});
        add(new int[]{0,1,0,0,1,0,0,1,0});
        add(new int[]{0,0,1,0,0,1,0,0,1});
        add(new int[]{1,0,0,0,1,0,0,0,1});
        add(new int[]{0,0,1,0,1,0,1,0,0});
    }};
    
    boolean isBingo(int[] board) {
        for (int[] bingo : bingos) {
            boolean result = true;
            for (int i=0; i<9; i++) {
                if (bingo[i] == 1 && board[i] != 1) {
                    result = false;
                    break;
                }
            }
            if (result) {
                return true;
            }
        } 
        return false;
    }
    
    public int solution(String[] board) {
        int[] first = new int[9];
        int[] second = new int[9];
        int cntF = 0;
        int cntS = 0;
        
        int idx = 0;
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (board[i].charAt(j) == 'O') {
                    first[idx] = 1;
                    cntF++;
                }
                else if (board[i].charAt(j) == 'X') {
                    second[idx] = 1;
                    cntS++;
                }
                idx++;
            }
        }
        
        if (cntF < cntS || cntF - cntS > 1) {
            return 0;
        }
        
        boolean bingoF = isBingo(first);
        boolean bingoS = isBingo(second);
        
        if (bingoF && bingoS || (bingoF && cntF==cntS) || (bingoS && cntS != cntF)) {
            return 0;
        }
        return 1;
    }
}
