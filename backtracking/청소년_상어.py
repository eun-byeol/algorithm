import sys
input = sys.stdin.readline

N = 4
data = [list(map(int, input().split())) for _ in range(N)]
pool = [[0] * N for _ in range(N)]
fish = [[] * 3 for _ in range(N*N+1)]
for i in range(N):
    for j in range(0, 2*N, 2):
        n = data[i][j]
        d = data[i][j+1] - 1
        r = i
        c = j // 2
        fish[n] = [r, c, d]
        pool[r][c] = n    

def fish_move(fish, origin):
    pool = [p[:] for p in origin]
    for n in range(1, N*N+1):
        if fish[n][0] == -1: # 삭제된 경우
            continue
        r, c, d = fish[n]
        for i in range(8):
            nd = (d+i) % 8
            nr = r + dr[nd]
            nc = c + dc[nd]
            if 0 <= nr < N and 0 <= nc < N and pool[nr][nc] != -1:
                tmp = pool[nr][nc]
                pool[r][c] = tmp
                pool[nr][nc] = n
                fish[n][0] = nr
                fish[n][1] = nc
                fish[n][2] = nd
                if tmp > 0:
                    fish[tmp][0] = r
                    fish[tmp][1] = c
                break
    return pool

def shark_move(r, c, d, pool):
    edible = []
    while True:
        r += dr[d]
        c += dc[d]
        if r < 0 or r >= N or c < 0 or c >= N:
            return edible
        if pool[r][c] != 0:
            edible.append((pool[r][c], r, c))
    return edible

def dfs(origin, fish_o, total, r, c, d):
    fish = [f[:] for f in fish_o]
    pool = fish_move(fish, origin)
    edible = shark_move(r, c, d, pool)
    if len(edible) == 0:
        result = max(result, total)
        return

    for e in edible:
        num, nr, nc = e
        nd = fish[num][2]
        pool[nr][nc] = -1 # 상어 위치
        pool[r][c] = 0
        fish[num][0] = -1 # 정보 삭제
        dfs(pool, fish, total + num, nr, nc, nd)
        pool[nr][nc] = num
        pool[r][c] = -1 # 상어 원위치
        fish[num][0] = nr

dr = [-1, -1, 0, 1, 1, 1, 0, -1] # 반시계
dc = [0, -1, -1, -1, 0, 1, 1, 1]

num = pool[0][0]
shark = [0, 0, fish[num][2]] # r, c, d
fish[num][0] = -1
pool[0][0] = -1 #상어가 있는 위치

result = 0
dfs(pool, fish, num, 0, 0, fish[num][2])
print(result)
