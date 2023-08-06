import sys
from itertools import product
input = sys.stdin.readline

T = 10
dice = list(map(int, input().split()))

index = [[1], [2], [3], [4], [5], [6, 21], [7], [8], [9], [10], [11, 27], [12], [13], [14], [15], [16, 29], [17], [18], [19], [20], [-1], [22], [23], [24], [25], [26], [20], [28], [24], [30], [31], [24]]
score = [0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 13, 16, 19, 25, 30, 35, 22, 24, 28, 27, 26, 0]


def dfs(depth, total, position):
    global result
    if depth == T:
        result = max(result, total)        
        return
    for horse in range(1, 5):
        number = dice[depth]
        cur = position[horse]
        tmp = cur
        if cur == -1:
            continue
        if cur == 5 or cur == 10 or cur == 15:
            cur = index[cur][1]
            number -= 1
        for _ in range(number):
            cur = index[cur][0]
            if cur == -1:
                break
        if cur != -1 and cur in position:
            continue
        position[horse] = cur
        dfs(depth+1, total + score[cur], position)
        position[horse] = tmp

data = [1, 2, 3, 4]
result = 0
position = [0, 0, 0, 0, 0]
dfs(0, 0, position)
print(result)
