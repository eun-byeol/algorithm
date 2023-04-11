def solution(m, n, puddles):
    graph = [[0] * (m+1) for _ in range(n+1)] # 위,왼 계산시 index 에러 고려하지 않기 위함
    for p in puddles:
        if len(p) == 0:
            break
        y, x = p
        graph[x][y] = -1
        
    graph[1][1] = 1
        
    for r in range(1, n+1):
        for c in range(1, m+1):
            if r == 1 and c == 1: # (1,1) = 1로 값이 고정되도록 건너뜀
                continue
            if graph[r][c] == -1:
                graph[r][c] = 0 # 웅덩이는 계산하지 않고, 0으로 값을 갱신
                continue
            graph[r][c] = (graph[r-1][c] + graph[r][c-1]) % 1000000007 # (1,1)을 제외하고 모두 0이므로 위,왼만 더해줌
    return graph[n][m]
