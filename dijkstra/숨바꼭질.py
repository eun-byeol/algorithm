import sys
import heapq
input = sys.stdin.readline
INF = int(1e9)

n, m = map(int, input().split())
data = [[] for _ in range(n+1)]
dist = [INF] * (n+1)

for _ in range(m):
  a, b = map(int, input().split())
  data[a].append((b, 1))
  data[b].append((a, 1))

q = [(0, 1)]
dist[1] = 0

while q:
  d, cur = heapq.heappop(q)
  if dist[cur] < d:
    continue
  for i in data[cur]:
    cost = d + i[1]
    if cost < dist[i[0]]:
      dist[i[0]] = cost
      heapq.heappush(q, (cost, i[0]))

max_num = 0
max_dist = 0
max_list = []
for i in range(1, n+1):
  if max_dist < dist[i]:
    max_dist = dist[i]
    max_num = i
    max_list = [i]
  elif max_dist == dist[i]:
    max_list.append(i)

print(max_num, max_dist, len(max_list))
