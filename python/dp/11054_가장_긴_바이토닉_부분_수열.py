import sys
input = sys.stdin.readline

N = int(input())
arr = list(map(int, input().rstrip().split()))

dp_up = [1] * N
dp_down = [1] * N

for i in range(N):
    for j in range(i):
        if arr[i] > arr[j]:
            dp_up[i] = max(dp_up[j]+1, dp_up[i])

for i in range(N-1, -1, -1):
    for j in range(N-1, i-1, -1):
        if arr[i] > arr[j]:
            dp_down[i] = max(dp_down[j]+1, dp_down[i])

total = [0] * N
max_v = 0
for i in range(N):
    total[i] = dp_up[i] + dp_down[i] - 1
    max_v = max(total[i], max_v)

print(max_v)
