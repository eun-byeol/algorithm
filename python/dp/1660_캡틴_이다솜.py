N = int(input())

data = [int(n * (n+1) * (2 * n + 1)/12 + n * (n + 1)/4) for n in range(1000)]

dp = [i for i in range(N+1)]

for i in range(1, N+1):
    for num in data:
        k = i - num
        if k < 0:
            break
        if dp[i] > dp[k] + 1:
            dp[i] = dp[k] + 1

print(dp[N])
