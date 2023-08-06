from itertools import permutations
INF = int(1e9)

def calculate(orders):
    total = abs(company[0] - orders[0][0]) + abs(company[1] - orders[0][1])
    for i in range(N-1):
        if total >= ans:
            return -1
        total += abs(orders[i][0] - orders[i+1][0]) + abs(orders[i][1] - orders[i+1][1])
    total += abs(orders[-1][0] - home[0]) + abs(orders[-1][1] - home[1])
    return total

T = int(input())
for test_case in range(1, T + 1):
    N = int(input())
    data = list(map(int, input().split()))
    company = data[:2]
    home = data[2:4]
    customers = []
    for i in range(4, 2*N+4, 2):
        customers.append((data[i], data[i+1]))

    ans = INF
    for permu in permutations(customers, N):
        rst = calculate(permu)
        if rst > 0:
            ans = min(rst, ans)
    print(f"#{test_case} {ans}")
