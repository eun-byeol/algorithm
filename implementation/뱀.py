import sys
from collections import deque
input = sys.stdin.readline

n = int(input())
k = int(input())

apple = [[False] * n for _ in range(n)]
for _ in range(k):
  i, j = map(int, input().split())
  apple[i-1][j-1] = True

l = int(input())
time = dict()
for _ in range(l):
  t, d = input().split()
  if d == 'D':
    time[int(t)] = 1
  else:
    time[int(t)] = -1

dr = [0, 1, 0, -1]
dc = [1, 0, -1, 0]
di = 0

now_time = 0
snake = deque([(0,0)])
r = 0
c = 0
while True:
  if now_time in time:
    di += time[now_time]
    di = (di+4) % 4
  now_time += 1
  
  r += dr[di]
  c += dc[di]
  if r<0 or r>=n or c<0 or c>=n:
    break
  if (r,c) in snake:
    break
  snake.append((r,c))
  if apple[r][c]:
    apple[r][c] = False
    continue
  snake.popleft()
print(now_time)
