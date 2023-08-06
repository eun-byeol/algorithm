import sys
input = sys.stdin.readline

def stack_tile_1(bord, y):
    x = 5
    for i in range(0, 6):
        if bord[i][y] != 0:
            x = i-1
            break
    bord[x][y] = 1
            
def stack_tile_1by2(bord, y): #가로
    x = 5
    for i in range(0, 6):
        if bord[i][y] != 0 or bord[i][y+1] != 0:
            x = i-1
            break
    bord[x][y] = 1
    bord[x][y+1] = 1
    
def stack_tile_2by1(bord, y): #세로
    x = 4
    for i in range(0, 5):
        if bord[i][y] != 0 or bord[i+1][y] != 0:
            x = i-1
            break
    bord[x][y] = 1
    bord[x+1][y] = 1

def stack_tile(t, x, y, green, blue):
    if t == 1:
        stack_tile_1(green, y)
        stack_tile_1(blue, 3-x)
    elif t == 2:
        stack_tile_1by2(green, y)
        stack_tile_2by1(blue, 3-x)
    else:
        stack_tile_2by1(green, y)
        stack_tile_1by2(blue, 2-x)

def get_score(bord):
    score = 0
    for i in range(2, 6):
        flag = 1
        for j in range(0, 4):
            if bord[i][j] == 0:
                flag = 0
                break
        if flag:
            score += 1
            for r in range(i, 0, -1): # 덮어 씌우기
                for c in range(0, 4):
                    bord[r][c] = bord[r-1][c]
            for c in range(0, 4):
                bord[0][c] = 0
    return score

def update_special_zone(bord):
    cnt = 0
    for i in range(0, 2):
        for j in range(0, 4):
            if bord[i][j] == 1:
                cnt += 1
                break
    for r in range(5, -1+cnt, -1): # 덮어 씌우기
        for c in range(0, 4):
            bord[r][c] = bord[r-cnt][c]
    for r in range(0, 2):
        for c in range(0, 4):
            bord[r][c] = 0

def count_tile(bord):
    cnt = 0
    for i in range(2, 6):
        for j in range(0, 4):
            if bord[i][j] == 1:
                cnt += 1
    return cnt

N = int(input())
green = [[0,0,0,0] for _ in range(6)]
blue = [[0,0,0,0] for _ in range(6)]
result = 0
cnt = 0
for _ in range(N):
    t, x, y = map(int, input().split())
    stack_tile(t, x, y, green, blue)
    result += get_score(green)
    result += get_score(blue)
    update_special_zone(green)
    update_special_zone(blue)
cnt += count_tile(green)
cnt += count_tile(blue)
print(result)
print(cnt)
