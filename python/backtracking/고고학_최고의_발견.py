import copy
answer = int(1e9)
N = 0
dr = [0, 1, 0, -1]
dc = [1, 0, -1, 0]

def move(r, c, n, board):
    board[r][c] = (board[r][c] + n) % 4
    for i in range(4):
        nr = r + dr[i]
        nc = c + dc[i]
        if 0 <= nr < N and 0 <= nc < N:
            board[nr][nc] = (board[nr][nc] + n) % 4

def simul(cnt, board):
    global answer
    for r in range(1, N):
        for c in range(N):
            if board[r-1][c] == 0:
                continue
            move_cnt = 4 - board[r-1][c]
            move(r, c, move_cnt, board)
            cnt += move_cnt
    if sum(board[N-1]) == 0:
        answer = min(cnt, answer)

def dfs(r, c, cnt, board):
    if c == N:
        simul(cnt, copy.deepcopy(board))
        return
    
    for i in range(4):
        dfs(r, c+1, cnt + i, board)
        move(r, c, 1, board)

def solution(clockHands):
    global N
    N = len(clockHands)
    dfs(0, 0, 0, clockHands)
    return answer
