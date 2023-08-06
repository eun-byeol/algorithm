n = int(input())
data = list(map(int, input().split()))

data.sort()

t = 1
for d in data:
  if t < d:
    break
  t += d

print(t)

'''
5
3 2 1 1 9
'''  
