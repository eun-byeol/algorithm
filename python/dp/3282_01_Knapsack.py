def solve(N, K, products):
    dp = [[0] * (K+1) for _ in range(N+1)]
    for i in range(1, N+1):
        v, c = products[i]
        for j in range(1, K+1):
            if v <= j:
                dp[i][j] = max(dp[i-1][j], dp[i-1][j-v] + c)
            else:
                dp[i][j] = dp[i-1][j]
    return dp[N][K]
    
T = int(input())
for test_case in range(1, T + 1):
    answer = 0
    N, K = map(int, input().split())
    products = [0]
    for _ in range(N):
        v, c = map(int, input().split())
        products.append((v, c))
    answer = solve(N, K, products)
    print(f"#{test_case} {answer}")
