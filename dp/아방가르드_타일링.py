def solution(n):
    dp = [0] * 100001
    dp[1:7] = [1, 3, 10, 23, 62, 170]
    mod = 1000000007

    for i in range(7, n+1):
        dp[i] = (dp[i-1] + 2*dp[i-2] + 6*dp[i-3] + dp[i-4] - dp[i-6]) % mod
    return dp[n]
