import sys
from collections import deque
input = sys.stdin.readline

N, K = map(int, input().split())
A = deque(list(map(int, input().split())))
robot = deque([0] * N)
UP = 0
DOWN = N-1

cycle = 0
while True:
    cycle += 1
    A.rotate(1)
    robot.rotate(1)
    robot[DOWN] = 0
    if sum(robot) > 0:
        for i in range(DOWN-1, -1, -1):
            if robot[i] == 0:
                continue
            if A[i+1] > 0 and robot[i+1] == 0:
                robot[i+1] = 1
                robot[i] = 0
                A[i+1] -= 1
        robot[DOWN] = 0
    if A[UP] > 0:
        robot[UP] = 1
        A[UP] -= 1
    if A.count(0) >= K:
        break
print(cycle)
