import sys
input = sys.stdin.readline

N, K = map(int, input().split())
chess = [list(map(int, input().split())) for _ in range(N)]
data = [[[] for _ in range(N)] for _ in range(N)]
pices = []
for i in range(K):
    r, c, d = map(int, input().split())
    data[r-1][c-1] = [i]
    pices.append([r-1, c-1, d-1])

def move(data, pices, num):
    r, c, d = pices[num]
    nr = r + dr[d]
    nc = c + dc[d]
    if nr < 0 or nr >= N or nc < 0 or nc >= N or chess[nr][nc] == 2:
        d = d+1 if d == 0 or d == 2 else d-1
        pices[num][2] = d
        nr = r + dr[d]
        nc = c + dc[d]
        if nr < 0 or nr >= N or nc < 0 or nc >= N or chess[nr][nc] == 2:
            return True

    tmp = []
    for idx, n in enumerate(data[r][c]):
            if n == num:
                tmp.extend(data[r][c][idx:])
                data[r][c] = data[r][c][:idx]
                
    if chess[nr][nc] == 1: # 빨
        tmp = tmp[-1::-1]

    data[nr][nc].extend(tmp)
    
    for t in tmp:
        pices[t][0] = nr
        pices[t][1] = nc

    if len(data[nr][nc]) >= 4: #종료조건
        return False
    return True        

dr = [0, 0, -1, 1] # 우 좌 상 하
dc = [1, -1, 0, 0]

result = 0
is_stop = False

for result in range(1, 1001):
    for i in range(K):
        if not move(data, pices, i):
            is_stop = True
            break
    if is_stop:
        break
if not is_stop:
    result = -1
print(result)
