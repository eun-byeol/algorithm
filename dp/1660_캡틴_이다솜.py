N = int(input())

cnts = [int(n * (n+1) * (2 * n + 1)/12 + n * (n + 1)/4) for n in range(1000)]
dp = [i for i in range(N+1)]
i = 1
while cnts[i] <= N:
    dp[cnts[i]] = 1
    i += 1

for i in range(N+1):
    for j in range(len(cnts)):
        k = i - cnts[j]
        if k < 0:
            break
        if dp[i] > dp[k] + 1:
           dp[i] = dp[k] + 1

print(dp[N])
