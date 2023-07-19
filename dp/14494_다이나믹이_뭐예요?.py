N, M = map(int, input().split())
dp = [[0] * (M+1) for _ in range(N+1)]
dp[0][0] = 1

dr = [0, 1, 1]
dc = [1, 0, 1]
mod = 1000000007

for r in range(N):
    for c in range(M):
        for i in range(3):
            nr = r + dr[i]
            nc = c + dc[i]
            dp[nr][nc] += dp[r][c]
            dp[nr][nc] %= mod

print(dp[N-1][M-1])
