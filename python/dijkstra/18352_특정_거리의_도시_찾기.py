import sys
from collections import deque
input = sys.stdin.readline
INF = int(1e9)

def bfs(start):
    dist = [INF] * (N+1)
    dist[start] = 0
    q = deque([start])
    while q:
        cur = q.popleft()
        if dist[cur] > K:
            break
        for nxt in graph[cur]:
            if dist[nxt] > dist[cur] + 1:
                dist[nxt] = dist[cur] + 1
                q.append(nxt)
    return dist


N, M, K, X = map(int, input().rstrip().split())
graph = [[] for _ in range(N+1)]
for _ in range(M):
    s, e = map(int, input().rstrip().split())
    graph[s].append(e)

dist = bfs(X)
ans_flag = 0
for i, d in enumerate(dist):
    if d == K:
        print(i)
        ans_flag = 1
if ans_flag == 0:
    print(-1)
