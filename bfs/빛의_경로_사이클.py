from collections import deque
N, M = 0, 0
dr = [0, 1, 0, -1] #동남서북
dc = [1, 0, -1, 0]
visited = []

def bfs(start_d, start_r, start_c, grid):
    global visited
    q = deque([])
    q.append((0, start_d, start_r, start_c))
    visited[start_d][start_r][start_c] = 1
    while q:
        dist, d, r, c = q.popleft()
        if grid[r][c] == 'L':
            d = (d-1)%4
        elif grid[r][c] == 'R':
            d = (d+1)%4
        nr = (r + dr[d]) % N
        nc = (c + dc[d]) % M
        if d == start_d and nr == start_r and nc == start_c:
            return dist+1
        if visited[d][nr][nc] == 1:
            return -1
        q.append((dist+1, d, nr, nc))
        visited[d][nr][nc] = 1
    return -1

def solution(grid):
    global N, M, visited
    answer = []
    N = len(grid)
    M = len(grid[0])
    visited = [[[0] * M for _ in range(N)] for _ in range(4)] #[d][r][c]
    
    for d in range(4):
        for r in range(N):
            for c in range(M):
                if visited[d][r][c] == 1:
                    continue
                cnt = bfs(d, r, c, grid)
                if cnt != -1:
                    answer.append(cnt)
    answer.sort()
    return answer
