def dfs(depth, r, c, board, N, visited, u_dia, l_dia):
    global answer
    if depth == N:
        answer += 2
        if N % 2 != 0 and board[0][N//2] == 1:
            answer -= 1
        return
    if r == 1 and c >= N-(N//2):
        return
    for i in range(r, N):
        for j in range(N):
            if visited[j] == 1:
                continue
            if u_dia[i+j] == 1 or l_dia[i-j+N] == 1:
                continue
            board[i][j] = 1
            visited[j] = 1
            u_dia[i+j], l_dia[i-j+N] = 1, 1
            dfs(depth+1, i+1, j, board, N, visited, u_dia, l_dia)
            board[i][j] = 0
            visited[j] = 0
            u_dia[i+j], l_dia[i-j+N] = 0, 0

def solve(N):
    global answer
    answer = 0
    board = [[0] * N for _ in range(N)]
    visited = [0] * N
    u_dia = [0] * (2*N)
    l_dia = [0] * (2*N)
    dfs(0, 0, -1, board, N, visited, u_dia, l_dia)
    return answer

T = int(input())
answer = 0

for test_case in range(1, T + 1):
    N = int(input())
    ans = solve(N)
    print(f"#{test_case} {ans}")
