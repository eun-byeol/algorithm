n = int(input())
data = list(map(int, input().split()))

data.sort()

x = 1;
i = 0;

while i < n:
  if data[i] > x:
    break
  x += 1
  i += x

print(x-1)

'''
5
2 3 1 2 2
'''
