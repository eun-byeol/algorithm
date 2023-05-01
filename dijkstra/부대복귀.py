from collections import deque

def bfs(n, roads, graph, end):
    visited = [0] * (n+1)
    dist = [-1] * (n+1)
    dist[end] = 0
    q = deque([end])
    visited[end] = 1
    while q:
        cur = q.popleft()
        for nxt in graph[cur]:
            if visited[nxt]:
                continue
            dist[nxt] = dist[cur] + 1
            q.append(nxt)
            visited[nxt] = 1
    return dist
    
def set_graph(n, roads):
    graph = [[] for _ in range(n+1)]
    for road in roads:
        v1, v2 = road
        if v1 != v2:
            graph[v1].append(v2)
            graph[v2].append(v1)
    return graph

def solution(n, roads, sources, destination):
    answer = []
    graph = set_graph(n, roads)
    dist = bfs(n, roads, graph, destination)
    for s in sources:
        answer.append(dist[s])
    return answer
