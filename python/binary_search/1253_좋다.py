import sys
input = sys.stdin.readline

def validate(data, target):
    s = 0
    e = len(data)-1
    while s < e:
        total = data[s] + data[e]
        if total == target:
            return True
        if total > target:
            e -= 1
        else:
            s += 1
    return False

N = int(input())
arr = list(map(int, input().rstrip().split()))

arr.sort()

cnt = 0
for i in range(N):
    data = arr[:i] + arr[i+1:]
    if validate(data, arr[i]):
       cnt += 1
print(cnt)
