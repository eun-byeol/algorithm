import sys
from collections import deque
input = sys.stdin.readline
INF = 1e9

n = int(input())
data = [list(map(int, input().split())) for _ in range(n)]
num = deque([i for i in range(n)])

def calculate(team):
  first = []
  second = []
  for i in range(n):
    if team[i] == 0:
      first.append(i)
    else:
      second.append(i)
  
  f_total = 0
  s_total = 0 
  for i in first:
    for j in first:
      f_total += data[i][j]
  for i in second:
    for j in second:
      s_total += data[i][j]
      
  if f_total > s_total:
    return f_total - s_total
  return s_total - f_total

def dfs(at, depth, team):
  global result
  if depth == n/2: #n-1개 중에서 (n/2-1)개 뽑기
    result = min(result, calculate(team))
    return
  for i in range(at, n):
    team[i] = 1
    dfs(i+1, depth+1, team)
    team[i] = 0

team = [0] * n
team[0] = 1
result = INF
dfs(1, 1, team)
print(result)
