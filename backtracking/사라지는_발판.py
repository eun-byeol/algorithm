INF = int(1e9)
dr = [0, -1, 0, 1]
dc = [-1, 0, 1 ,0]

def is_moveable(r, c, board, N, M):
    for i in range(4):
        nr = r + dr[i]
        nc = c + dc[i]
        if 0 <= nr < N and 0 <= nc < M and board[nr][nc]:
            return True
    return False

def dfs(my_r, my_c, your_r, your_c, board, N, M):
    if not is_moveable(my_r, my_c, board, N, M):
        return False, 0
    if my_r == your_r and my_c == your_c:
        return True, 1
    
    max_move = 0
    min_move = INF
    is_winner = False
    for i in range(4):
        nr = my_r + dr[i]
        nc = my_c + dc[i]
        if 0 > nr or nr >= N or 0 > nc or nc >= M or not board[nr][nc]:
            continue
        board[my_r][my_c] = 0
        is_succeed, move = dfs(your_r, your_c, nr, nc, board, N, M)
        board[my_r][my_c] = 1
        
        if not is_succeed: # 내가 이기는 경우 -> 최소 이동 횟수
            is_winner = True
            min_move = min(move, min_move)
        elif not is_winner: # 내가 지는 경우 -> 최대 이동 횟수
            max_move = max(move, max_move)
    my_move = min_move + 1
    if not is_winner:
        my_move = max_move + 1
    return is_winner, my_move
        
def solution(board, aloc, bloc):
    answer = int(1e9)
    N = len(board)
    M = len(board[0])
    flag, answer = dfs(aloc[0], aloc[1], bloc[0], bloc[1], board, N, M)
    return answer
