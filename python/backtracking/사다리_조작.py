import sys
input = sys.stdin.readline
INF = 1e9

N, M, H = map(int, input().split())
bridge = [[0] * N for _ in range(H+1)]
for _ in range(M):
  a, b = map(int, input().split())
  bridge[a][b] = 1

def simul(bridge):
  for h in range(1, N+1):
    cur = h
    for i in range(1, H+1):
      if cur > 1:
        if bridge[i][cur-1]:
          cur -= 1
          continue
      if cur < N:
        if bridge[i][cur]:
          cur += 1
    if cur != h:
      return False
  return True

def dfs(depth, bridge, max_h, at_i, at_j):
  global result
  if depth >= result:
    return
  if simul(bridge):
    result = depth
  if depth == max_h:
    return
  for i in range(at_i, H+1):
    if i != at_i:
      at_j = 1
    for j in range(at_j, N):
      if bridge[i][j]:
        continue
      if j-1 >= 0 and bridge[i][j-1]:
        continue
      if j+1 < N and bridge[i][j+1]:
        continue
      bridge[i][j] = 1
      dfs(depth + 1, bridge, max_h, i, j+2)
      bridge[i][j] = 0

result = INF
dfs(0, bridge, 3, 1, 1)
if result == INF:
  result = -1
print(result)
