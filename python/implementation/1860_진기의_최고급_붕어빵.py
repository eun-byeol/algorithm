def solve(M, K, time):
    time.sort()
    for i, t in enumerate(time):
        able = (t//M) * K - i
        if able == 0:
            return False
    return True

T = int(input())
for test_case in range(1, T + 1):
    N, M, K = map(int, input().split())
    time = list(map(int, input().split()))
    answer = "Impossible"
    if solve(M, K, time):
        answer = "Possible"
    print(f"#{test_case} {answer}")
