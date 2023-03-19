import sys
from collections import deque
input = sys.stdin.readline

N, M = map(int, input().split())
data = [list(map(int, input().split())) for _ in range(N)]

def straight(graph, d, x, y):
  while True:
    x += dr[d]
    y += dc[d]
    if x < 0 or x >= N or y<0 or y >= M:
      return
    if graph[x][y] == 6: 
      return
    if graph[x][y] == 0:
      graph[x][y] = 7

def simulate(graph, perfo, x, y):
  tmp = [v[:] for v in graph]
  for d in perfo:
    straight(tmp, d, x, y)
  return tmp

def calculate(graph):
  cnt = 0
  for i in range(N):
    for j in range(M):
      if graph[i][j] == 0:
        cnt += 1
  return cnt

def dfs(cctv, graph):
  if not cctv:
    global result
    result = min(calculate(graph), result)
    return 
  v = cctv.popleft()
  num, x, y = v
  for perfo in performance[num]:
    dfs(cctv, simulate(graph, perfo, x, y))
  cctv.append(v)

dr = [-1, 0, 1, 0] #상우하좌
dc = [0, 1, 0, -1]

performance = [
  [],
  [[0], [1], [2], [3]],
  [[0,2], [1,3]],
  [[0,3], [0,1], [1,2], [2,3]],
  [[0,1,2], [0,1,3], [0,2,3], [1,2,3]],
  [[0,1,2,3]]
]

cctv = deque([])
for i in range(N):
  for j in range(M):
    if 0 < data[i][j] < 6:
      cctv.append((data[i][j], i, j))

result = 1e9

dfs(cctv, data)
print(result)
