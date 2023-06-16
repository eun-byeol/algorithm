from collections import deque

def find_rm_area(board, m, n):
    result = set()
    dr = [1, 0, 1]
    dc = [0, 1, 1]
    for r in range(m-1):
        for c in range(n-1):
            if board[r][c] == '_':
                continue
            rm = {(r, c)}
            for d in range(3):
                nr = r + dr[d]
                nc = c + dc[d]
                if board[nr][nc] != board[r][c]:
                    break
                rm.add((nr, nc))
            if len(rm) == 4:
                result.update(rm)
    return result

def remove(rm_info, board):
    for r, c in rm_info:
        board[r][c] = '_'
        
def rearrange(board, m, n):
    for c in range(n-1, -1, -1):
        q = deque([])
        for r in range(m-1, -1, -1):
            if board[r][c] == '_':
                q.append((r, c))
                continue
            if not q:
                continue
            x, y = q.popleft()
            board[x][y], board[r][c] = board[r][c], board[x][y]
            q.append((r, c))

def solution(m, n, board):
    answer = 0
    board = [list(b) for b in board]
    
    while True:
        rm_info = find_rm_area(board, m, n)
        if len(rm_info) == 0:
            break
        answer += len(rm_info)
        remove(rm_info, board)
        rearrange(board, m, n)
    return answer
