import heapq
INF = int(1e9)
N = 0
graph = []

def make_graph(paths):
    global graph
    graph = [[] for _ in range(N+1)]
    for v1, v2, e in paths:
        graph[v1].append((v2, e))
        graph[v2].append((v1, e))

def dijkstra(gates, summits):
    intensity = [INF] * (N+1)
    visited = [0] * (N+1)
    h = []
    for gate in gates:
        intensity[gate] = 0
        heapq.heappush(h, (0, gate))
        
    while h:
        cur_e, cur = heapq.heappop(h)
        visited[cur] = 1
        if cur in summits or cur_e > intensity[cur]:
            continue
            
        for v, e in graph[cur]:
            if visited[v] == 1:
                continue
            max_e = max(e, cur_e)
            if max_e < intensity[v]:
                intensity[v] = max_e
                heapq.heappush(h, (max_e, v))
    return intensity
        
def solution(n, paths, gates, summits):
    global N
    answer = [INF, INF]
    N = n
    make_graph(paths)
    intensity = dijkstra(gates, set(summits))
    
    summits.sort()
    for summit in summits:
        if intensity[summit] < answer[1]:
            answer[1] = intensity[summit]
            answer[0] = summit
    return answer

# ver. bfs
from collections import deque
INF = int(1e9)
graph = []
N = 0

def bfs(q, visited, gates, summits):
    answer = [INF, INF]
    summits_set = set(summits)
    while q:
        e, v = q.popleft()
        if e > answer[1]:
            continue
        if v in summits_set:
            if e < answer[1] or (e == answer[1] and v < answer[0]):
                answer = [v, e]
            continue
        for next_v, next_e in graph[v]:
            max_e = max(next_e, e)
            if visited[next_v] == -1:
                continue
            if 0 < visited[next_v] <= max_e:
                continue
            q.append((max_e, next_v))
            visited[next_v] = max_e
    return answer

def make_graph(paths):
    global graph
    graph = [[] for _ in range(N+1)]
    for v1, v2, e in paths:
        graph[v1].append((v2, e))
        graph[v2].append((v1, e))
        
def solution(n, paths, gates, summits):
    global N
    N = n
    make_graph(paths)
    q = deque()
    visited = [0] * (N+1)
    for gate in gates:
        q.append((0, gate))
        visited[gate] = -1
    return bfs(q, visited, gates, summits)
