import sys
input = sys.stdin.readline

r, c, k = map(int, input().split())
r -= 1
c -= 1
data = [[0] * 100 for _ in range(100)]
for i in range(3):
    data[i][:3] = map(int, input().split())

def calculate_row(data, row_size, col_size):
    max_len = 0
    for r in range(row_size):
        cnt = {}
        for c in range(col_size):
            if data[r][c] == 0: continue
            cnt[data[r][c]] = cnt.get(data[r][c], 0) + 1
        row = sorted(cnt.items(), key=lambda x : (x[1], x[0]))
        i = 0
        for c in range(0, 100, 2):
            if i < len(row):
                data[r][c] = row[i][0]
                data[r][c+1] = row[i][1]
                i += 1
            else:
                if c >= col_size: 
                    break
                data[r][c:c+2] = 0, 0
                
        max_len = max(len(row)*2, max_len)
    return max_len

def calculate_col(data, row_size, col_size):
    max_len = 0
    for c in range(col_size):
        cnt = {}
        for r in range(row_size):
            if data[r][c] == 0: continue
            cnt[data[r][c]] = cnt.get(data[r][c], 0) + 1
        col = sorted(cnt.items(), key=lambda x : (x[1], x[0]))
        i = 0
        for r in range(0, 100, 2):
            if i < len(col):
                data[r][c] = col[i][0]
                data[r+1][c] = col[i][1]
                i += 1
            else:
                if r >= row_size:
                    break
                data[r][c] = 0
                data[r+1][c] = 0
        max_len = max(len(col)*2, max_len)
    return max_len

time = 0
row_size, col_size = 3, 3
while time < 101:
    if data[r][c] == k:
        break
    if row_size >= col_size:
        col_size = calculate_row(data, row_size, col_size)
    else:
        row_size = calculate_col(data, row_size, col_size)
    time += 1
if time > 100:
    time = -1
print(time)
