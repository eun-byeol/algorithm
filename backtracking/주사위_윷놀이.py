import sys
from itertools import product
input = sys.stdin.readline

T = 10
dice = list(map(int, input().split()))

index = [[1], [2], [3], [4], [5], [6, 21], [7], [8], [9], [10], [11, 27], [12], [13], [14], [15], [16, 29], [17], [18], [19], [20], [-1], [22], [23], [24], [25], [26], [20], [28], [24], [30], [31], [24]]
score = [0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0, 13, 16, 19, 25, 30, 35, 22, 24, 28, 27, 26]

# p = [1, 1, 1, 1, 2, 2, 2, 3, 4, 4]
# p = [1, 1, 1, 1, 2, 2, 2, 2, 4, 4]

def calculate(p, dice):
    total = 0
    position = [0, 0, 0, 0, 0]
    for i in range(T):
        horse = p[i]
        number = dice[i]
        cur = position[horse]
        if cur == -1: # 도착한 말을 꺼내는 경우 없음
            # print(1)
            return 0
        if cur == 5 or cur == 10 or cur == 15: # 출발이 파란색 지점일 때, 1칸 먼저 이동
            cur = index[cur][1]
            number -= 1
        for _ in range(number): # 이동하기
            cur = index[cur][0]
            if cur == -1: #도착한 경우
                break
        if cur != -1 and cur in position: # 말이 겹치는 경우 없음
            # print(2)
            return 0
        total += score[cur]
        position[horse] = cur
    return total

data = [1, 2, 3, 4]
result = 0
for p in list(product(data, repeat=T)):
    result = max(calculate(p, dice), result)
print(result)
