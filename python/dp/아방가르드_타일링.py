def solution(n):
    dp = [0] * 100001
    dp[1:4] = [1, 3, 10]
    s = [0, 1, 2, 5, 2, 2, 4]
    mod = 1000000007
    
    for i in range(4, 7): # 4 ~ 6까지 구하기
        for j in range(1, i):
            dp[i] += s[j] * dp[i-j]
        dp[i] += s[i]
    
    for i in range(7, n+1): # 7이상 구하기
        dp[i] = (dp[i-1] + 2*dp[i-2] + 6*dp[i-3] + dp[i-4] - dp[i-6]) % mod
    return dp[n]
