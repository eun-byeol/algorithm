def get_points(line):
    point_x = []
    point_y = []
    for i, info in enumerate(line):
        a, b, e = info
        for c, d, f in line[i+1:]:
            if a*d - b*c == 0:
                continue
            x = (b*f - e*d) / (a*d - b*c)
            y = (e*c - a*f) / (a*d - b*c)
            if int(x) == x and int(y) == y:
                point_x.append(int(x))
                point_y.append(int(y))
    return point_x, point_y

def solution(line):
    point_x, point_y = get_points(line)

    max_r = max(point_y)
    min_r = min(point_y)
    max_c = max(point_x)
    min_c = min(point_x)
    
    R = max_r - min_r + 1
    C = max_c - min_c + 1
    board = [['.' for _ in range(C)] for _ in range(R)]
          
    for x, y in zip(point_x, point_y):
        board[max_r-y][abs(min_c-x)] = "*"
    answer = []
    for bd in board:
        answer.append(''.join(bd))
    return answer
