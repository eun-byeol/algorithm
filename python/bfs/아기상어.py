import sys
from collections import deque
input = sys.stdin.readline
INF = 1e9

N = int(input())
graph = [list(map(int, input().split())) for _ in range(N)]
shark = 2
curx, cury = -1, -1
for i in range(N):
    for j in range(N):
        if graph[i][j] == 9:
            curx, cury = i, j
            graph[i][j] = 0
            break
    if curx != -1: 
        break

def bfs(shark, x, y, fish):
    visited = [[0]*N for _ in range(N)]
    q = deque([ [x, y, 0] ])
    min_dist = INF
    
    while q:
        cx, cy, dist = q.popleft()
        visited[cx][cy] = 1
        if dist > min_dist:
            return min_dist
            
        if 0 < graph[cx][cy] < shark:
            min_dist = dist
            fish.append((cx, cy))
        
        for i in range(4):
            nx = cx + dr[i]
            ny = cy + dc[i]
            if nx < 0 or nx >= N or ny < 0 or ny >= N:
                continue
            if not visited[nx][ny] and graph[nx][ny] <= shark:
                q.append((nx, ny, dist+1))
                visited[nx][ny] = 1
    if min_dist == INF:
        return 0
    return min_dist

dr = [-1, 0, 0, 1]
dc = [0, -1, 1, 0]
result = 0
cnt = 0

while True:
    fish = []
    dist = bfs(shark, curx, cury, fish)
    if dist == 0:
        break
    fish.sort()
    curx, cury = fish[0]
    graph[curx][cury] = 0
    cnt += 1
    if cnt == shark:
        shark += 1
        cnt = 0
    result += dist
print(result)
