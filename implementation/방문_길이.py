def solution(dirs):
    answer = 0
    N = 21
    board = [[0] * N for _ in range(N)]
    dr = {"U": (-1, 0), "D": (1, 0), "R": (0, 1), "L": (0, -1)}
    r, c = 10, 10
    for d in dirs:
        nr = r + dr[d][0]
        nc = c + dr[d][1]
        if nr < 0 or nr >= N or nc < 0 or nc >= N:
            continue
        if board[nr][nc] == 0:
            answer += 1
        board[nr][nc] = 1
        nr += dr[d][0]
        nc += dr[d][1]
        board[nr][nc] = 1
        r, c = nr, nc
    return answer
