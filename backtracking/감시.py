import sys
from collections import deque
input = sys.stdin.readline

N, M = map(int, input().split())
data = [list(map(int, input().split())) for _ in range(N)]

def calculate(graph):
  cnt = 0
  for i in range(N):
    for j in range(M):
      if graph[i][j] == 0:
        cnt += 1
  return cnt

def dfs(deque, graph):
  if not deque:
    global result
    result = min(calculate(graph), result)
    return 
  v = deque.popleft()
  num = v[0]
  x = v[1]
  y = v[2]
  if num == 1:
    for i in range(4):
      dfs(deque, move(graph, [i], x, y))
  elif num == 2:
    dfs(deque, move(graph, [0,2], x, y))
    dfs(deque, move(graph, [1,3], x, y))
  elif num == 3:
    dfs(deque, move(graph, [0,1], x, y))
    dfs(deque, move(graph, [0,3], x, y))
    dfs(deque, move(graph, [1,2], x, y))
    dfs(deque, move(graph, [2,3], x, y))
  elif num == 4:
    dfs(deque, move(graph, [0,1,2], x, y))
    dfs(deque, move(graph, [0,1,3], x, y))
    dfs(deque, move(graph, [0,2,3], x, y))
    dfs(deque, move(graph, [1,2,3], x, y))
  else: # num == 5
    dfs(deque, move(graph, [0,1,2,3], x, y))
  deque.append(v)

def straight(graph, di, x, y):
  while True:
    x += dr[di]
    y += dc[di]
    if x < 0 or x >= N or y<0 or y >= M:
      return
    if graph[x][y] == 6: 
      return
    if graph[x][y] == 0:
      graph[x][y] = 7

def move(graph, di_list, x, y):
  tmp = [v[:] for v in graph]
  for di in di_list:
    straight(tmp, di, x, y)
  return tmp

dr = [-1, 0, 1, 0] #상우하좌
dc = [0, 1, 0, -1]
result = 1e9
deque = deque([])
for i in range(N):
  for j in range(M):
    if 0 < data[i][j] < 6:
      deque.append((data[i][j], i, j))
      
dfs(deque, data)
print(result)
