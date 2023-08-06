import sys
input = sys.stdin.readline

n = int(input())
stu = list(map(int, input().split()))
b, c = map(int, input().split())

result = 0

for s in stu:
  s -= b
  result += 1
  if s <= 0:
    continue
  result += s//c
  if s % c:
    result += 1
  
print(result)
