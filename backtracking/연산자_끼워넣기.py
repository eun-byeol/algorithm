import sys
input = sys.stdin.readline
INF = 1e9

n = int(input())
num = list(map(int, input().split()))
pl, mi, mul, div = map(int, input().split())

def dfs(depth, result, pl, mi, mul, div):
  global max_v, min_v
  if depth == n:
    max_v = max(max_v, result)
    min_v = min(min_v, result)
    return
  if pl:
    dfs(depth+1, result + num[depth], pl-1, mi, mul, div)
  if mi:
    dfs(depth+1, result - num[depth], pl, mi-1, mul, div)
  if mul:
    dfs(depth+1, result * num[depth], pl, mi, mul-1, div)
  if div:
    dfs(depth+1, int(result / num[depth]), pl, mi, mul, div-1)

max_v = -INF
min_v = INF
dfs(1, num[0], pl, mi, mul, div)
print(max_v)
print(min_v)
