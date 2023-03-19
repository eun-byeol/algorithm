import sys
input = sys.stdin.readline

N, M, H = map(int, input().split())
bridge = [[0] * N for _ in range(H+1)]
for _ in range(M):
  a, b = map(int, input().split())
  bridge[a][b] = 1

def simul(bridge):
  for h in range(1, N+1):
    cur = h
    for i in range(1, H+1):
      if cur != 1:
        if bridge[i][cur-1]:
          cur -= 1
          continue
      if cur != N:
        if bridge[i][cur]:
          cur += 1
    if cur != h:
      return False
  return True

def dfs(depth, bridge, size, at_i, at_j):
  global result
  if depth == size:
    if simul(bridge):
      result = depth
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
      bridge[i][j] = 2
      dfs(depth + 1, bridge, size, i, j+1)
      bridge[i][j] = 0

result = -1
if simul(bridge):
  result = 0
else:
  for i in range(1, 4):
    dfs(0, bridge, i, 1, 1)
    if result > 0:
      break
print(result)
