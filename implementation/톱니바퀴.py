import sys
from collections import deque
input = sys.stdin.readline
data = [deque(list(map(int, input().rstrip()))) for _ in range(4)]
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

for _ in range(K):
  n, d = map(int, input().split())
  dr[n-1] = d
  decide_dr(n-1, d)
  for i in range(4):
    data[i].rotate(dr[i])

total = 0
for i in range(4):
  if data[i][0] == 1:
    total += 1<<i

print(total)
