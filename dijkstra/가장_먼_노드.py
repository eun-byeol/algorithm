from collections import deque

def bfs(graph, n):
    max_value = 20000
    dist = [max_value] * (n+1)
    dist[1] = 0
    q = deque([(1)])
    while q:
        idx = q.popleft()
        for next in graph[idx]:
            if dist[idx]+1 < dist[next]:
                q.append(next)
                dist[next] = dist[idx]+1
    for i in range(n+1):
        if dist[i] == max_value:
            dist[i] = 0
    return dist

def solution(n, edge):
    graph = [[] for _ in range(n+1)]
    for v1, v2 in edge:
        graph[v1].append(v2)
        graph[v2].append(v1)
    distance = bfs(graph, n)
    return distance.count(max(distance))
