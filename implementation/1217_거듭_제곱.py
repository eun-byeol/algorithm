def recursive(cnt, N):
    if cnt == 0:
        return 1
    return N * recursive(cnt-1, N)

T = 10
for _ in range(1, T + 1):
    test_case = int(input())
    N, M = map(int, input().split())
    answer = recursive(M, N)
    print(f"#{test_case} {answer}")
