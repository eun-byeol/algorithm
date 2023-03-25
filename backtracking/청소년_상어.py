import sys
import copy
input = sys.stdin.readline

N = 4
pool = [[0] * N for _ in range(N)]
fish = [[0,0,0] for _ in range(N*N+1)]

for i in range(N):
    data = list(map(int, input().split()))
    for j in range(0, 2*N, 2):
        n = data[j]
        d = data[j+1] - 1
        r = i
        c = j // 2
        fish[n] = [r, c, d]
        pool[r][c] = n

def fish_move(fish, pool, shark_x, shark_y):
    for n in range(1, N*N+1):
        if fish[n][2] == -1: # 삭제된 경우 d = -1
            continue
        r, c, d = fish[n]
        for i in range(8):
            nd = (d+i) % 8
            nr = r + dr[nd]
            nc = c + dc[nd]
            if 0 <= nr < N and 0 <= nc < N and (nr != shark_x or nc != shark_y):
                nn = pool[nr][nc]
                pool[r][c], pool[nr][nc] = pool[nr][nc], pool[r][c]
                fish[n][0], fish[n][1], fish[n][2] = nr, nc, nd
                fish[nn][0], fish[nn][1] = r, c
                break

def shark_move(r, c, d, pool):
    edible = []
    while True:
        r += dr[d]
        c += dc[d]
        if r < 0 or r >= N or c < 0 or c >= N:
            return edible
        if pool[r][c] != 0:
            edible.append(pool[r][c])
    return edible

def dfs(pool, fish, total, num):
    global result
    shark_x, shark_y, shark_d = fish[num]
    total += num
    pool[shark_x][shark_y] = 0
    fish[num][2] = -1 # 삭제된 경우 d = -1

    fish_move(fish, pool, shark_x, shark_y)
    edible = shark_move(shark_x, shark_y, shark_d, pool)
    if len(edible) == 0:
        result = max(result, total)
        return
    for e in edible:
        dfs(copy.deepcopy(pool), copy.deepcopy(fish), total, e)

dr = [-1, -1, 0, 1, 1, 1, 0, -1] # 반시계
dc = [0, -1, -1, -1, 0, 1, 1, 1]

num = pool[0][0]
d = fish[num][2]

result = 0
dfs(pool, fish, 0, pool[0][0])
print(result)
