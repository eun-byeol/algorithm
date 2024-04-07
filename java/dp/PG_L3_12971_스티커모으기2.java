import java.util.*;

class Solution {
    int[] stickers;
    int n;
    
    public int solution(int sticker[]) {
        stickers = sticker;
        n = sticker.length;
        
        int ans1 = cal(0, n-1);
        int ans2 = cal(1, n);
        return Math.max(ans1, ans2);
    }
    
    int cal(int start, int end) {
        if (n == 1) {
            return stickers[0];
        }
        int[] dp = new int[end];
        dp[start] = stickers[start];
        for (int i=start+1; i < end; i++) {
            if (i-2 >= 0) {
                dp[i] = Math.max(dp[i-1], dp[i-2] + stickers[i]);
                continue;
            }
            dp[i] = Math.max(dp[i-1], stickers[i]);
        }
        return dp[end-1];
    }
}
