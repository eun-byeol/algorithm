import sys
input = sys.stdin.readline

R, C, M = map(int, input().split())
graph = [[0]*C for _ in range(R)]
for _ in range(M):
    r, c, s, d, z = map(int, input().split())
    graph[r-1][c-1] = [s, d-1, z]

def catch_shark(c):
    for r in range(R):
        if graph[r][c] == 0:
            continue
        size = graph[r][c][2]
        graph[r][c] = 0
        return size
    return 0

def next_position(r, c):
    s, d, z = graph[r][c]
    if d == 0 or d == 1: # 상, 하
        dist = R-1-r if d == 1 else r
        if dist < s:
            s -= dist
            cycle = s // (R-1) + 1
            if cycle % 2 != 0: # 홀수인 경우 방향전환
                d = ((2*d+1) + d) % 4
            s = s % (R-1)
            r = 0 if d == 1 else R-1 # 출발 위치 갱신
        nr = r + s * dr[d]
        return [nr, c, d]
        
    if d == 2 or d == 3: # 우, 좌
        dist = c if d==3 else C-1-c
        if dist < s:
            s -= dist
            cycle = s // (C-1) + 1
            if cycle % 2 != 0: # 홀수인 경우 방향전환
                d = ((2*d+1) + d) % 4
            s = s % (C-1)
            c = C-1 if d == 3 else 0 # 출발 위치 갱신
        nc = c + s * dc[d]
        return [r, nc, d]

def move_shark():
    tmp = [[0]*C for _ in range(R)]
    for i in range(R):
        for j in range(C):
            if graph[i][j] == 0:
                continue
            s, d, z = graph[i][j]
            r, c, d = next_position(i, j)

            if tmp[r][c] != 0 and z < tmp[r][c][2]:
                continue
            tmp[r][c] = (s, d, z)
    return tmp

dr = [-1, 1, 0, 0] # 상, 하, 우, 좌
dc = [0, 0, 1, -1]

result = 0
for c in range(C):
    result += catch_shark(c)
    graph = move_shark()
print(result)
