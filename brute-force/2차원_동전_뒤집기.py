answer = -1
N, M = 0, 0
t_graph = []

def flip_row(r, graph):
    for c in range(M):
        graph[r][c] = 0 if graph[r][c] == 1 else 1
    return graph

def check(c, graph):
    correct = 0
    for r in range(N):
        if graph[r][c] == t_graph[r][c]:
            correct += 1
    if correct == N: # 뒤집기가 불필요한 경우
        return 0
    if correct == 0: # 뒤집기가 필요한 경우
        return 1
    return -1 # 불가능한 경우

def dfs(row, cnt, graph):
    global answer
    if row == N:
        for col in range(M):
            extra_cnt = check(col, graph)
            if extra_cnt == -1:
                return
            cnt += extra_cnt
        if answer == -1 or cnt < answer:
            answer = cnt
        return
            
    graph = flip_row(row, graph)
    dfs(row+1, cnt+1, graph)
    graph = flip_row(row, graph)
    dfs(row+1, cnt, graph)

def solution(beginning, target):
    global N, M, t_graph
    N = len(beginning)
    M = len(beginning[0])
    t_graph = target
    
    dfs(0, 0, beginning)
    return answer
