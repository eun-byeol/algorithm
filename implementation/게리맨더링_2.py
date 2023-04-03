import sys
input = sys.stdin.readline
INF = 1e9

N = int(input())
A = [list(map(int, input().split())) for _ in range(N)]

def simul(x, y, d1, d2, total):
    population = [0,0,0,0,0]
    # 1번
    c = y+1
    for r in range(x+d1):
        if r >= x:
            c -= 1
        population[0] += sum(A[r][:c])
    # 2번
    c = y+1
    for r in range(x+d2+1):
        if r > x:
            c += 1
        population[1] += sum(A[r][c:])
    # 3번
    c = y-d1
    for r in range(x+d1, N):
        population[2] += sum(A[r][:c])
        if r < x+d1+d2:
            c += 1
    # 4번
    c = y+d2
    for r in range(x+d2+1, N):
        population[3] += sum(A[r][c:])
        if r <= x+d1+d2:
            c -= 1
    # 5번
    population[4] = total - sum(population)
    return max(population) - min(population)

total = 0
for arr in A:
    total += sum(arr)

result = INF

for x in range(0, N-2):
    for y in range(1, N-1):
        for d1 in range(1, N-1):
            for d2 in range(1, N-1):
                if x+d1+d2 <= N-1 and 0 <= y-d1 and y+d2 <= N-1:
                    result = min(simul(x, y, d1, d2, total), result)
print(result)
