import sys
input = sys.stdin.readline
N = int(input())
A = [list(map(int, input().split())) for _ in range(N)]

def is_valid_idx(r, c):
    return 0 <= r < N and 0 <= c < N

def spread(yr, yc, d, A):
    y = A[yr][yc]
    if y == 0:
        return
    A[yr][yc] = 0
    
    ar, ac = yr + dr[d], yc + dc[d]
    if is_valid_idx(ar, ac):
        a = y - 2 * (int(y*0.1) + int(y*0.07) + int(y*0.02) + int(y*0.01)) - int(y*0.05)
        A[ar][ac] += a
    
    r5, c5 = ar + dr[d], ac + dc[d]
    if is_valid_idx(r5, c5):
        A[r5][c5] += int(y*0.05)

    xr, xc = yr - dr[d], yc - dc[d]
    for i in range(-1, 2, 2):
        nd = (d + i) % 4
        r10, c10 = ar + dr[nd], ac + dc[nd]
        if is_valid_idx(r10, c10):
            A[r10][c10] += int(y*0.1)
            
        r7, c7 = yr + dr[nd], yc + dc[nd]
        if is_valid_idx(r7, c7):
            A[r7][c7] += int(y*0.07)

        r2, c2 = r7 + dr[nd], c7 + dc[nd]
        if is_valid_idx(r2, c2):
            A[r2][c2] += int(y*0.02)

        r1, c1 = xr + dr[nd], xc + dc[nd]
        if is_valid_idx(r1, c1):
            A[r1][c1] += int(y*0.01)

def move(A):
    r = N // 2
    c = r
    d, cnt = 0, 1
    while True:
        for _ in range(2):
            for _ in range(cnt):
                r = r + dr[d]
                c = c + dc[d]
                if r == 0 and c == -1:
                    return
                spread(r, c, d, A)
            d = (d + 1) % 4
        cnt += 1
    
dr = [0, 1, 0, -1] # 서남동북
dc = [-1, 0, 1, 0]

before = 0
for arr in A:
    before += sum(arr)
move(A)
after = 0
for arr in A:
    after += sum(arr)
print(before - after)
