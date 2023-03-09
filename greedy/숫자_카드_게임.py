n, m = map(int, input().split())

mx = 0
for i in range(n):
  data = map(int, input().split())
  mn = min(data)
  mx = max(mn, mx)

print(mx)

'''
3 3
3 1 2
4 1 4
2 2 2

2 4
7 3 1 8
3 3 3 4
'''
