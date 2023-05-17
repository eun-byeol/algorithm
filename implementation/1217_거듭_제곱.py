def recursive(depth, total, N, M):
    global answer
    if depth == M:
        answer = total
        return
    recursive(depth+1, total*N, N, M)

T = 10
for _ in range(1, T + 1):
    test_case = int(input())
    N, M = map(int, input().split())
    answer = 0
    recursive(1, N, N, M)
    print(f"#{test_case} {answer}")
