def solve(origin):
    cnt, cur = 0, '0'
    for bit in origin:
        if cur != bit:
            cnt += 1
            cur = '0' if cur == '1' else '1'
    return cnt

T = int(input())
for test_case in range(1, T + 1):
    origin = input()
    N = len(origin)
    answer = solve(origin)
    print(f"#{test_case} {answer}")
