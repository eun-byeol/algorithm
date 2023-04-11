from collections import deque
def bfs(idx, value, total, graph, n):
    visited = [0] * n
    q = deque([idx])
    visited[idx] = 1
    while q:
        cur = q.popleft()
        for nxt, is_value in enumerate(graph[cur]):
            if is_value == value and not visited[nxt]:
                q.append(nxt)
                visited[nxt] = 1
    total[idx] += sum(visited) -1

def solution(n, results):
    graph = [[0] * n for _ in range(n)]
    for w, l in results:
        graph[w-1][l-1] = 1
        graph[l-1][w-1] = -1
    total = [0] * n
    for i in range(n):
        bfs(i, 1, total, graph, n)
        bfs(i, -1, total, graph, n)
    return total.count(n-1)
