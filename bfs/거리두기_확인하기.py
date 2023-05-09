from collections import deque
dr = [0, 1, 0, -1]
dc = [1, 0, -1, 0]

def is_valid(x, y, place):
    visited = [[0] * 5 for _ in range(5)]
    q = deque()
    q.append((x, y, 0))
    visited[x][y] = 1
    while q:
        r, c, dist = q.popleft()
        if dist == 3:
            return True
        if place[r][c] == 'P' and dist != 0:
            return False
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
            if 0 > nr or nr >= 5 or 0 > nc or nc >= 5 or visited[nr][nc] == 1:
                continue
            if place[nr][nc] == 'X':
                continue
            q.append((nr, nc, dist+1))
            visited[nr][nc] = 1
    return True

def solve(place):
    for i in range(5):
        for j in range(5):
            if place[i][j] == 'P':
                if not is_valid(i, j, place):
                    return 0
    return 1

def solution(places):
    answer = []
    for place in places:
        answer.append(solve(list(place)))
    return answer
