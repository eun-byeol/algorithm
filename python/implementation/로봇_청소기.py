import sys
input = sys.stdin.readline

n, m = map(int, input().split())
r, c, d = map(int, input().split())
data = [list(map(int, input().split())) for _ in range(n)]

dr = [-1, 0, 1, 0] #북동남서
dc = [0, 1, 0, -1]

def isEmpty(data, i, j):
  if i-1 > 0 and data[i-1][j] == 0:
    return True
  if j+1 < m and data[i][j+1] == 0:
    return True
  if i+1 < n and data[i+1][j] == 0:
    return True
  if j-1 > 0 and data[i][j-1] == 0:
    return True
  return False

result = 0
while True:
  if data[r][c] == 0:
    data[r][c] = 2
    result += 1
  if isEmpty(data, r, c):
    d = (3+d) % 4 #방향 전환
    nr = r + dr[d]
    nc = c + dc[d]
    if nr<0 or nr>=n or nc<0 or nc>=m:
      continue
    if data[nr][nc] == 0:
      r = nr
      c = nc
  else:
    nr = r - dr[d]
    nc = c - dc[d]
    if nr<0 or nr>=n or nc<0 or nc>=m:
      break
    if data[nr][nc] == 1:
      break
    r = nr
    c = nc

print(result)
