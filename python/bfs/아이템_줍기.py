from collections import deque
def bfs(cx, cy, ix, iy, graph):
    dr = [0, 0, 1, -1]
    dc = [1, -1, 0, 0]
    q = deque([(cx, cy)])
    graph[cx][cy] = 0
    while q:
        r, c = q.popleft()
        if r == ix and c == iy:
            return graph[r][c] // 2
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
            if 0 < nr < 102 and 0 < nc < 102 and graph[nr][nc] == -1:
                graph[nr][nc] = graph[r][c] + 1
                q.append((nr, nc))
    return -1

def solution(rectangle, characterX, characterY, itemX, itemY):
    graph = [[1] * 102 for _ in range(102)]
    
    for rec in rectangle:
        x1, y1, x2, y2 = map(lambda x : x*2, rec)
        for r in range(x1, x2+1):
            for c in range(y1, y2+1):
                if x1 < r < x2 and y1 < c < y2:
                    graph[r][c] = 0
                elif graph[r][c] != 0:
                    graph[r][c] = -1

    return bfs(characterX*2, characterY*2, itemX*2, itemY*2, graph)
