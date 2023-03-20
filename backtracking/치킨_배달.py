import sys
input = sys.stdin.readline
INF = 1e9

def simul(chicken):
  total = 0
  for x,y in house:
    dist = INF
    for r,c in chicken:
      dist = min(dist, abs(x-r) + abs(y-c))
    total += dist
  return total

def dfs(left_ck, at):
  global result
  if len(left_ck) == M:
    result = min(result, simul(left_ck))
    return
  for i in range(at, C):
    x, y = chicken[i]
    if (C-M) < M: #제거하는 치킨집 뽑기
      left_ck.remove((x,y))
      dfs(left_ck, i+1)
      left_ck.append((x,y))
    else: #남겨두는 치킨
      left_ck.append((x,y))
      dfs(left_ck, i+1)
      left_ck.remove((x,y))

N, M = map(int, input().split())
data = [list(map(int, input().split())) for _ in range(N)]
chicken = []
house = []
for i in range(N):
  for j in range(N):
    if data[i][j] == 1:
      house.append((i,j))
    elif data[i][j] == 2:
      chicken.append((i,j))
C = len(chicken)

result = INF
if (C-M) < M: #제거할 치킨집 뽑기
  dfs(chicken.copy(), 0)
else:
  dfs([], 0) #남겨둘 치킨집 뽑기
print(result)
