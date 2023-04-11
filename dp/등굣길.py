def solution(m, n, puddles):
    graph = [[0] * (m+1) for _ in range(n+1)]
    for p in puddles:
        if len(p) == 0:
            break
        y, x = p
        graph[x][y] = -1
        
    graph[1][1] = 1
        
    for r in range(1, n+1):
        for c in range(1, m+1):
            if graph[r][c] == -1:
                continue
            if graph[r-1][c] != -1:
                graph[r][c] += graph[r-1][c]
            if graph[r][c-1] != -1:
                graph[r][c] += graph[r][c-1]
    return graph[n][m] % 1000000007
