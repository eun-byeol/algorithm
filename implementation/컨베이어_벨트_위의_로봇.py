import sys
from collections import deque
input = sys.stdin.readline

N, K = map(int, input().split())
A = list(map(int, input().split()))
conveyor = deque([])
for i in range(2*N):
    conveyor.append([A[i], 0])
UP = 0
DOWN = N-1

cycle = 0
zero = 0
while zero < K:
    cycle += 1
    conveyor.rotate(1)
    conveyor[DOWN][1] = 0 # 내리기
    for i in range(DOWN-1, -1, -1):
        if conveyor[i][1] == 0:
            continue
        if conveyor[i+1][0] > 0 and conveyor[i+1][1] == 0:
            conveyor[i+1][1], conveyor[i][1] = conveyor[i][1], conveyor[i+1][1]
            conveyor[i+1][0] -= 1
            if conveyor[i+1][0] == 0:
                zero += 1
    conveyor[DOWN][1] = 0 # 내리기
    if conveyor[UP][0] > 0:
        conveyor[UP][1] = 1
        conveyor[UP][0] -= 1
        if conveyor[UP][0] == 0:
            zero += 1
print(cycle)
