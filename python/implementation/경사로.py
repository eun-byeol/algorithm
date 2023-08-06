import sys
input = sys.stdin.readline

N, L = map(int, input().split())
data = [list(map(int, input().split())) for _ in range(N)]

def validate(load):
  bridge = [False] * N
  for i in range(1, N):
    cur = load[i]
    pre = load[i-1]

    if abs(cur - pre) > 1:
      return False

    if cur > pre:
      for j in range(L):
        pi = i-j-1
        if pi < 0 or bridge[pi] or load[pi] != cur-1:
          return False
        bridge[pi] = True

    elif cur < pre:
      for j in range(L):
        ni = i+j
        if ni >= N or load[ni] != cur:
          return False
        bridge[ni] = True
  return True

result = 0
rev_data = list(zip(*data))

for i in range(N):
  if validate(data[i]):
    result += 1
  if validate(rev_data[i]):
    result += 1
  
print(result)
