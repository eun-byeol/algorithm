import sys
from collections import deque
input = sys.stdin.readline

def bfs(x, y, visited):
  queue = deque([(x,y)])
  sum = 0
  uni = []

  while queue:
    x, y = queue.popleft()
    visited[x][y] = 1
    sum += data[x][y]
    uni.append((x,y))

    for d in range(4):
      nx = x + dr[d]
      ny = y + dc[d]
      if nx < 0 or nx >= N or ny < 0 or ny >= N: 
        continue
      if not visited[nx][ny]:
        diff = abs(data[x][y] - data[nx][ny])
        if L <= diff <= R:
          queue.append((nx,ny))
          visited[nx][ny] = 1
          
  average = int(sum / len(uni))
  for x,y in uni:
    data[x][y] = average

N, L, R = map(int, input().split())
data = [list(map(int, input().split())) for _ in range(N)]

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

result = 0
while True:
  union_size = 0
  visited = [[0]*N for _ in range(N)]
  for i in range(N):
    for j in range(N):
      if not visited[i][j]:
        bfs(i, j, visited)
        union_size += 1
  if union_size == N*N:
    break
  result += 1
print(result)
