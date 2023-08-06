from collections import deque
def bfs(x, y, maps, n ,m):
    dr = [0, 0, 1, -1]
    dc = [1, -1, 0, 0]
    q = deque([ (x, y, 1) ])
    maps[0][0] = 2
    
    while q:
        r, c , dist = q.popleft()
        if r == n-1 and c == m-1:
            return dist
        
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
            if 0 <= nr < n and 0 <= nc < m and maps[nr][nc] == 1:
                maps[nr][nc] = 2
                q.append((nr, nc, dist+1))
    return -1

def solution(maps):
    n = len(maps)
    m = len(maps[0])
    return bfs(0, 0, maps, n, m)
