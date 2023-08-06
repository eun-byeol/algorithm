import sys
import heapq
INF = int(1e9)
input = sys.stdin.readline

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

for tc in range(int(input())):
  n = int(input())
  data = [list(map(int, input().split())) for _ in range(n)]
  dist = [[INF] * n for _ in range(n)]  
  
  q = [(data[0][0], 0, 0)]
  dist[0][0] = data[0][0]
  while q:
    d, r, c = heapq.heappop(q)
    if dist[r][c] < d:
      continue
    for i in range(4):
      nr = r + dr[i]
      nc = c + dc[i]
      if nr < 0 or nr >= n or nc < 0 or nc >= n:
        continue
      cost = d + data[nr][nc]
      if cost < dist[nr][nc]:
        dist[nr][nc] = cost
        heapq.heappush(q, (cost, nr, nc))
  print(dist[n-1][n-1])
