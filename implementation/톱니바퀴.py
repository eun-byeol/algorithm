data = [list(map(int, input())) for _ in range(4)]
K = int(input())

dr = [0, 0, 0, 0]

def decide_dr(n, d):
  for i in range(n, 3):
    if data[i][2] == data[i+1][6]:
      dr[i+1] = 0
    else:
      dr[i+1] = -dr[i]

  for i in range(n, 0, -1):
    if data[i][6] == data[i-1][2]:
      dr[i-1] = 0
    else:
      dr[i-1] = -dr[i]

def shift():
  for i in range(4):
    if dr[i] == 0:
      continue
    if dr[i] == 1:
      tmp = data[i][7]
      for j in range(7, 0, -1):
        data[i][j] = data[i][j-1]
      data[i][0] = tmp
    else :
      tmp = data[i][0]
      for j in range(7):
        data[i][j] = data[i][j+1]
      data[i][7] = tmp

for _ in range(K):
  n, d = map(int, input().split())
  dr[n-1] = d
  decide_dr(n-1, d)
  shift()

total = 0
for i in range(4):
  if data[i][0] == 1:
    total += 1<<i

print(total)
