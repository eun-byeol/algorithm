def init(N):
    board = [[0] * N for _ in range(N)]
    board[N//2][N//2] = 2
    board[N//2-1][N//2-1] = 2
    board[N//2][N//2-1] = 1
    board[N//2-1][N//2] = 1
    return board

def simul(r, c, color):
    board[r][c] = color
    for d in range(8):
        opp = []
        is_closed = 0
        nr, nc = r, c
        while True:
            nr += dr[d]
            nc += dc[d]
            if 0 > nr or nr >= N or 0 > nc or nc >= N or board[nr][nc] == 0:
                break
            if board[nr][nc] == color:
                is_closed = 1
                break
            opp.append((nr, nc))
        if is_closed:
            for opp_r, opp_c in opp:
                board[opp_r][opp_c] = color

dr = [0, 1, 0, -1, -1, -1, 1, 1]
dc = [1, 0, -1, 0, -1, 1, -1, 1]

T = int(input())
for test_case in range(1, T + 1):
    global board, N
    N, M = map(int, input().split())
    board = init(N)
    is_stop = False
    for _ in range(M):
        x, y, color = map(int, input().split())
        simul(y-1, x-1, color)

    black_cnt = 0
    white_cnt = 0
    for row in board:
        black_cnt += row.count(1)
        white_cnt += row.count(2)
    print(f"#{test_case} {black_cnt} {white_cnt}")
