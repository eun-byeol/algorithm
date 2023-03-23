import sys
from collections import deque
input = sys.stdin.readline

N, K = map(int, input().split())
chess = [list(map(int, input().split())) for _ in range(N)]
data = [[[] for _ in range(N)] for _ in range(N)]
pices = [[] for _ in range(K+1)]
for i in range(1, K+1):
    r, c, d = map(int, input().split())
    data[r-1][c-1] = [i]
    pices[i] = [r-1, c-1, d-1]

def move(data, pices, num):
    r, c, d = pices[num]
    tmp = deque([])
    for i in range(2):
        nr = r + dr[d]
        nc = c + dc[d]
        if 0 <= nr < N and 0 <= nc < N and chess[nr][nc] != 2:
            if chess[nr][nc] == 0: # 흰
                while data[r][c]:
                    v = data[r][c].pop()
                    tmp.appendleft(v)
                    if v == num:
                        break
            elif chess[nr][nc] == 1: # 빨
                while data[r][c]:
                    v = data[r][c].pop()
                    tmp.append(v)
                    if v == num:
                        break
            data[nr][nc].extend(tmp)
            if len(data[nr][nc]) >= 4: # 종료조건
                return False
            for t in tmp:
                pices[t][0] = nr
                pices[t][1] = nc
            pices[num][2] = d
            return True
        # 파, 체스판 벗어나는 경우
        if i == 0:
            d = d+1 if d == 0 or d == 2 else d-1
        pices[num][2] = d
    return True        

dr = [0, 0, -1, 1] # 우 좌 상 하
dc = [1, -1, 0, 0]

result = 0
is_stop = False

for result in range(1, 1001):
    for i in range(1, K+1):
        if pices[i] != []:
            if not move(data, pices, i):
                is_stop = True
                break
    if is_stop:
        break
if not is_stop:
    result = -1
print(result)
