def dfs(total, at, arr, K, N):
    global answer
    if total == K:
        answer += 1
        return
    elif total > K:
        return
    for i in range(at, N):
        dfs(total+arr[i], i+1, arr, K, N)

T = int(input())
for test_case in range(1, T + 1):
    global answer
    answer = 0
    N, K = map(int, input().split())
    arr = list(map(int, input().split()))
    arr.sort()
    dfs(0, 0, arr, K, N)
    print(f"#{test_case} {answer}")
