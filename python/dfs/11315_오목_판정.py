def dfs(r, c, depth, di, board, N):
    global is_valid
    if depth == 5:
        is_valid = 1
        return
    nr = r + dr[di]
    nc = c + dc[di]
    if 0 <= nr < N and 0 <= nc < N and board[nr][nc] == 'o':
        dfs(nr, nc, depth+1, di, board, N)
        
def solve(N, board):
    global is_valid
    is_valid = 0
    for r in range(N):
        for c in range(N):
            if board[r][c] == 'o':
                for i in range(4):
                    dfs(r, c, 1, i, board, N)
                    if is_valid == 1:
                        return "YES"
    return "NO"
    
dr = [0, 1, 1, 1] # 가로, 세로, 상향, 하향
dc = [1, 0, -1, 1]

T = int(input())
for test_case in range(1, T + 1):
    N = int(input())
    board = []
    for _ in range(N):
        board.append(input())
    answer = solve(N, board)
    print(f"#{test_case} {answer}")
