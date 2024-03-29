import sys
from collections import deque
from itertools import combinations
INF = 1e9
input = sys.stdin.readline

N, M = map(int, input().split())
data = [list(map(int, input().split())) for _ in range(N)]

def simul(combi, data, remain):
    result = 0
    visited = [d[:] for d in data]
    for i, j, t in combi:
        visited[i][j] = -2
    q = deque(combi)
    while q:
        r, c, time = q.popleft()
        if remain == 0:
            return result
        for d in range(4):
            nr = r + dr[d]
            nc = c + dc[d]
            if nr < 0 or nr >= N or nc < 0 or nc >= N:
                continue
            if visited[nr][nc] == 0 or visited[nr][nc] == -1:
                if visited[nr][nc] == 0:
                    remain -= 1
                q.append((nr, nc, time+1))
                result = time + 1
                visited[nr][nc] = time + 1
    return INF
    
virus = []
remain = 0
for i in range(N):
    for j in range(N):
        if data[i][j] == 0:
            remain += 1
        if data[i][j] == 2:
            virus.append((i,j, 0))
            data[i][j] = -1

result = INF
dr = [1, 0, -1, 0]
dc = [0, 1, 0, -1]

for combi in combinations(virus, M):
    result = min(result, simul(combi, data, remain))

if result == INF:
    result = -1
print(result)
