N = int(input())
dp = [0, 1, -1, 1]

for i in range(4, N+1):
    if dp[i-1] == 1 or dp[i-3] == 1:
        dp.append(-1)
    else:
        dp.append(1)

print("CY") if dp[N] == 1 else print("SK")
