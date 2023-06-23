from collections import defaultdict
import heapq

INF = int(1e9)

def make_graph(N, road):
    vertex = defaultdict(set)
    edge = [[INF] * (N+1) for _ in range(N+1)]
    for v1, v2, e in road:
        vertex[v1].add(v2)
        vertex[v2].add(v1)
        edge[v1][v2] = min(edge[v1][v2], e)
        edge[v2][v1] = min(edge[v2][v1], e)
    return vertex, edge

def dijkstra(vertex, edge, N, start):
    distance = [INF] * (N+1)
    distance[start] = 0
    h = []
    heapq.heappush(h, (0, start))
    while h:
        dist, cur = heapq.heappop(h)
        if distance[cur] < dist:
            continue
        for nxt in vertex[cur]:
            d = edge[cur][nxt] + dist
            if d < distance[nxt]:
                distance[nxt] = d
                heapq.heappush(h, (d, nxt))
    return distance

def solution(N, road, K):
    vertex, edge = make_graph(N, road)
    distance = dijkstra(vertex, edge, N, 1)
    answer = 0
    for i in range(1, N+1):
        if distance[i] <= K:
            answer += 1
    return answer
