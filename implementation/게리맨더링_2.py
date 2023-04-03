import sys
from itertools import product
input = sys.stdin.readline
INF = 1e9

N = int(input())
A = [list(map(int, input().split())) for _ in range(N)]

def simul(x, y, d1, d2):
    graph = [[0] * N for _ in range(N)]
    # 경계선
    for i in range(d1+1):
        graph[x+i][y-i] = 5
        graph[x+d2+i][y+d2-i] = 5
    for j in range(d2+1):
        graph[x+j][y+j] = 5
        graph[x+d1+j][y-d1+j] = 5

    # 1번
    for i in range(x+d1):
        for j in range(y+1):
            if graph[i][j] == 5: break
            graph[i][j] = 1
    # 2번
    for i in range(x+d2+1):
        for j in range(N-1, y, -1):
            if graph[i][j] == 5: break
            graph[i][j] = 2
    # 3번
    for i in range(x+d1, N):
        for j in range(y-d1+d2):
            if graph[i][j] == 5: break
            graph[i][j] = 3
    # 4번
    for i in range(x+d2+1, N):
        for j in range(N-1, y-d1+d2-1, -1):
            if graph[i][j] == 5: break
            graph[i][j] = 4
    # 5번
    for i in range(x, x+d1+d2+1):
        for j in range(y-d1, y+d2+1):
            if graph[i][j] == 0:
                graph[i][j] = 5

    population = [0,0,0,0,0]
    for i in range(N):
        for j in range(N):
            num = graph[i][j]
            population[num-1] += A[i][j]
    return max(population) - min(population)

distance = list(range(1, N-1))
result = INF

for x in range(0, N-2):
    for y in range(1, N-1):
        for d1, d2 in product(distance, repeat=2):
            if x+d1+d2 <= N-1 and 0 <= y-d1 and y+d2 <= N-1:
                result = min(simul(x, y, d1, d2), result)
print(result)
