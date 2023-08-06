import sys
from itertools import combinations
input = sys.stdin.readline
INF = 1e9

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

result = INF
for combi in combinations(chicken, M):
  total = 0
  for x,y in house:
    dist = INF
    for r,c in combi:
      dist = min(dist, abs(x-r) + abs(y-c))
    total += dist
  result = min(result, total)

print(result)
