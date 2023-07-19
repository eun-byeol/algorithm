N, M = map(int, input().split())
dp = [[0] * (M+1) for _ in range(N+1)]
dp[0][0] = 1

mod = int(1e9) + 7

for r in range(1, N+1):
    for c in range(1, M+1):
        dp[r][c] = (dp[r][c-1] + dp[r-1][c] + dp[r-1][c-1]) % mod

print(dp[N][M])
