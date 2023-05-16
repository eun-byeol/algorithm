def get_index(n):
    cur = 0
    x = 0
    for i in range(1, n+1):
        cur += i
        if cur >= n:
            x = i
            break
    diff = cur - n
    return x-diff, 1+diff

def get_number(x, y):
    cur = 0
    for i in range(1, x+1):
        cur += i
    for j in range(x, x+y-1):
        cur += j
    return cur

def solve(p, q):
    x1, y1 = get_index(p)
    x2, y2 = get_index(q)
    return get_number(x1+x2, y1+y2)

T = int(input())
for test_case in range(1, T + 1):
    p, q = map(int, input().split())
    answer = solve(p, q)
    print(f"#{test_case} {answer}")
