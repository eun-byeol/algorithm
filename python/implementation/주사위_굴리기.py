import sys
input = sys.stdin.readline

n, m, x, y, k = map(int, input().split())
mp = [list(map(int, input().split())) for _ in range(n)]
command = list(map(int, input().split()))

dice = [0] * 6

def move(dice, cmd):
  if cmd == 1: #동
    tmp = dice[3]
    dice[3] = dice[2]
    dice[2] = dice[1]
    dice[1] = dice[5]
    dice[5] = tmp

  elif cmd == 2: #서
    tmp = dice[1]
    dice[1] = dice[2]
    dice[2] = dice[3]
    dice[3] = dice[5]
    dice[5] = tmp

  elif cmd == 3: #북
    tmp = dice[5]
    dice[5] = dice[4]
    dice[4] = dice[2]
    dice[2] = dice[0]
    dice[0] = tmp

  else: #남
    tmp = dice[0]
    dice[0] = dice[2]
    dice[2] = dice[4]
    dice[4] = dice[5]
    dice[5] = tmp  

dx = [0, 0, -1, 1] #동서북남
dy = [1, -1, 0, 0]
bottom_i = 2
top_i = 5

for cmd in command:
  nx = x + dx[cmd-1]
  ny = y + dy[cmd-1]
  if nx<0 or nx>=n or ny<0 or ny>=m: 
    continue
  x = nx
  y = ny

  move(dice, cmd)
  if mp[x][y] == 0:
    mp[x][y] = dice[bottom_i]
  else:
    dice[bottom_i] = mp[x][y]
    mp[x][y] = 0
    
  print(dice[top_i])
 
'''
주사위 도면 인덱스:
 0
123
 4
 5
'''
