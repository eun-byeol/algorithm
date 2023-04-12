from collections import deque
def bfs(cx, cy, ix, iy, graph):
    dr = [0, 0, 1, -1]
    dc = [1, -1, 0, 0]
    q = deque([(cx, cy, 0)])
    graph[cx][cy] = 2
    while q:
        r, c, dist = q.popleft()
        if r == ix and c == iy:
            return dist // 2
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
            if 0 < nr < 102 and 0 < nc < 102 and graph[nr][nc] == 1:
                graph[nr][nc] = 2
                q.append((nr, nc, dist+1))
    return -1

def solution(rectangle, characterX, characterY, itemX, itemY):
    graph = [[0] * 102 for _ in range(102)]

    for x1, y1, x2, y2 in rectangle:
        for r in range(x1*2, x2*2+1):
            for c in range(y1*2, y2*2+1):
                graph[r][c] = 1

    for x1, y1, x2, y2 in rectangle:
        for r in range(x1*2+1, x2*2):
            for c in range(y1*2+1, y2*2):
                graph[r][c] = 0

    return bfs(characterX*2, characterY*2, itemX*2, itemY*2, graph)
