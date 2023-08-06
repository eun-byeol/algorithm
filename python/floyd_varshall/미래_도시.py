INF = int(1e9)

n, m = map(int, input().split())
graph = [[INF] * (n+1) for _ in range(n+1)]

for i in range(1, n+1):
  graph[i][i] = 0

for _ in range(m):
  v1, v2 = map(int, input().split())
  graph[v1][v2] = 1
  graph[v2][v1] = 1

x, k = map(int, input().split())

for i in range(1, n+1):
  for a in range(1, n+1):
    for b in range(1, n+1):
      graph[a][b] = min(graph[a][b], graph[a][i]+graph[i][b])

result = graph[1][k] + graph[k][x]

if result >= INF:
  print("-1")
else:
  print(result)

'''
5 7
1 2
1 3
1 4
2 4
3 4 
3 5
4 5
4 5
'''
