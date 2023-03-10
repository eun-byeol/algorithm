import sys
input = sys.stdin.readline
INF = int(1e9)

n = int(input())
m = int(input())
dist = [[INF] * (n+1) for _ in range(n+1)]

for i in range(1, n+1):
  dist[i][i] = 0

for _ in range(m):
  a, b, c = map(int, input().split())
  dist[a][b] = min(dist[a][b], c)

for k in range(1, n+1):
  for x in range(1, n+1):
    for y in range(1, n+1):
      if x == y: 
        continue
      dist[x][y] = min(dist[x][y], dist[x][k] + dist[k][y])

for i in range(1, n+1):
  for j in range(1, n+1):
    if dist[i][j] < INF:
      print(dist[i][j], end=" ")
    else:
      print(0, end=" ")
  print()
