import sys
input = sys.stdin.readline

R, C, T = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(R)]
machine = [-1, 0]
for i in range(R):
    if graph[i][0] == -1:
        machine[0] = i
        break

def spread(data, r, c):
    v = int(graph[r][c] / 5)
    cnt = 0
    for i in range(4):
        nr = r + dr[i]
        nc = c + dc[i]
        if nr < 0 or nr >= R or nc <0 or nc >=C: 
            continue
        if 0 <= graph[nr][nc]:
            data[nr][nc] += v
            cnt += 1
    data[r][c] += graph[r][c] - v * cnt

def machine_work(dr, dc, x, y, is_stop):
    idx = 0
    r = x + dr[idx]
    c = y + dc[idx]
    while True:
        nr = r + dr[idx]
        nc = c + dc[idx]
        if nr == x and nc == y:
            break
        if is_stop(nr, nc, x):
            idx += 1
            continue
        graph[r][c] = graph[nr][nc]
        r, c = nr, nc
    graph[x][y+1] = 0
    graph[x][y] = 0

def measure_dust():
    total = 0
    for i in range(R):
        for j in range(C):
            if graph[i][j] > 0:
                total += graph[i][j]
    return total
    
dr_rev = [-1, 0, 1, 0]
dc_rev = [0, 1, 0, -1]

dr = [1, 0, -1, 0]
dc = [0, 1, 0, -1]

def m1_is_stop(r, c, x):
    return r < 0 or r > x or c < 0 or c >= C
def m2_is_stop(r, c, x):
    return r < x or r >= R or c < 0 or c >= C

for _ in range(T):
    tmp = [[0]*C for _ in range(R)]
    for i in range(R):
        for j in range(C):
            if graph[i][j] > 0:
                spread(tmp, i, j)
    graph = tmp
    machine_work(dr_rev, dc_rev, machine[0], machine[1], m1_is_stop)
    machine_work(dr, dc, machine[0]+1, machine[1], m2_is_stop)
    
result = measure_dust()
print(result)
