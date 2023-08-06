import sys
from collections import deque
input = sys.stdin.readline

N, M, T = map(int, input().split())
graph = [deque(list(map(int, input().split()))) for _ in range(N)]
command = []
for _ in range(T):
    xi, di, ki = map(int, input().split())
    command.append((xi, di, ki))


def cycle(x, d, k):
    for i in range(x-1, N, x):
        if d == 0: 
            graph[i].rotate(k)
        elif d == 1:
            graph[i].rotate(-k)

def update():
    sum = 0
    cnt = 0
    for i in range(N):
        for j in range(M):
            sum += graph[i][j]
            if graph[i][j] != 0:
                cnt += 1
    if cnt == 0:
        return False
    average = sum / cnt

    for i in range(N):
        for j in range(M):
            if 0 < graph[i][j] < average: 
                graph[i][j] += 1
            elif graph[i][j] > average: 
                graph[i][j] -= 1
    return True

def bfs(a, b, visited):
    q = deque([ (a, b) ])
    v = graph[a][b]
    cnt = 0
    while q:
        r, c = q.popleft()
        visited[r][c] = 1

        for i in range(4):
            nr = r + dr[i]
            nc = (c + dc[i]) % M
            if nr < 0 or nr >= N or visited[nr][nc]: 
                continue
            if graph[nr][nc] != 0 and graph[nr][nc] == v:
                cnt += 1
                graph[nr][nc] = 0
                visited[nr][nc] = 1
                q.append((nr, nc))
    if cnt > 0 :
        graph[a][b] = 0
        return True
    return False
    

def delete():
    visited = [[0] * M for _ in range(N)]
    delete_flag = 0
    for i in range(N):
        for j in range(M):
            if bfs(i, j, visited):
                delete_flag = 1
    if not delete_flag:
        return update()
    return True

dr = [1, -1, 0, 0]
dc = [0, 0, 1, -1]

for x, d, k in command:
    cycle(x, d, k)
    if not delete():
        break

result = 0
for i in range(N):
    result += sum(graph[i])
print(result)
