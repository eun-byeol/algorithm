from collections import deque
def bfs(graph, n, m):
    visited = [[0] * m for _ in range(n)]
    q = deque([(0,0)])
    graph[0][0] = 1
    visited[0][0] = 1
    while q:
        r, c = q.popleft()
        if r-1 >= 0 and graph[r-1][c] != -1:
            graph[r][c] += graph[r-1][c] % 1000000007
        if c-1 >= 0 and graph[r][c-1] != -1:
            graph[r][c] += graph[r][c-1] % 1000000007
        if r+1 < n and graph[r+1][c] != -1 and not visited[r+1][c]:
            visited[r+1][c] = 1
            q.append((r+1, c))
        if c+1 < m and graph[r][c+1] != -1 and not visited[r][c+1]:
            visited[r][c+1] = 1
            q.append((r, c+1))
    return graph[n-1][m-1] % 1000000007
        
def solution(m, n, puddles):
    graph = [[0] * m for _ in range(n)]
    for p in puddles:
        if len(p) == 0:
            break
        graph[p[1]-1][p[0]-1] = -1
    answer = bfs(graph, n, m)
    return answer
