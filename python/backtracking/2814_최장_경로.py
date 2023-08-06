def dfs(cur, dist, visited, graph):
    global max_l
    for nxt in graph[cur]:
        if visited[nxt] == 0:
            visited[nxt] = 1
            dfs(nxt, dist+1, visited, graph)
            visited[nxt] = 0
    max_l = max(dist, max_l)

def solve(graph, N):
    global max_l
    max_l = 0
    for i in range(1, N+1):
        visited = [0] * (N+1)
        visited[i] = 1
        dfs(i, 1, visited, graph)
    return max_l
    
T = int(input())
for test_case in range(1, T + 1):
    N, M = map(int, input().split())
    graph = [[] for _ in range(N+1)]
    for _ in range(M):
        v1, v2 = map(int, input().split())
        graph[v1].append(v2)
        graph[v2].append(v1)
    answer = solve(graph, N)
    print(f"#{test_case} {answer}")
