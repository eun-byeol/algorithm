from collections import defaultdict, deque
N = 50
dr = [0, 1, 0, -1]
dc = [1, 0, -1, 0]

def bfs(x, y, graph, visited, value):
    q = deque([(x, y)])
    visited[x][y] = 1
    indexes = [(x,y), (0, 0)] # 처음 위치 저장, (0,0)에서 시작
    while q:
        r, c = q.popleft()
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
            if 0 <= nr < N and 0 <= nc < N and not visited[nr][nc]:
                if graph[nr][nc] == value:
                    q.append((nr, nc))
                    visited[nr][nc] = 1
                    indexes.append((nr-x, nc-y))
    return len(indexes)-1, indexes

def scan_pices(value, graph):
    pices = defaultdict(list)
    visited = [[0]*N for _ in range(N)]
    for i in range(N):
        for j in range(N):
            if graph[i][j] == value and not visited[i][j]:
                num, indexes = bfs(i, j, graph, visited, value)
                pices[num].append(indexes)
    return pices

def is_equal(p1, p2):
    if len(p1) != len(p2):
        return False
    for idx in p1[1:]:
        if idx not in p2[1:]:
            return False
    return True

def remove_piece_on_table(table, piece):
    x, y = piece[0]
    for a, b in piece[1:]:
        table[x+a][y+b] = 0

def rotated(table):
    tmp = zip(*table[::-1])
    return [list(e) for e in tmp]

def solution(game_board, table):
    global N
    answer = 0
    N = len(game_board)
    board = scan_pices(0, game_board)

    for _ in range(4):
        my_table = scan_pices(1, table)
        if len(my_table) == 0:
            break
        for num in my_table: # 1 ~ 6
            for p1 in my_table[num]:
                for idx, p2 in enumerate(board[num]):
                    if len(p2) == 0: continue
                    if is_equal(p1, p2):
                        answer += num
                        board[num][idx].clear() # 보드 삭제
                        remove_piece_on_table(table, p1) # 사용한 조각 삭제
                        break
        table = rotated(table)
    return answer
