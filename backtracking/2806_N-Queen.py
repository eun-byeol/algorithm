def dfs(r, col, u_dia, l_dia, N):
    global answer
    if r == N:
        answer += 1
        return
    for c in range(N):
        if col[c] or u_dia[r+c] or l_dia[r-c+N]:
            continue
        col[c] = u_dia[r+c] = l_dia[r-c+N] = 1
        dfs(r+1, col, u_dia, l_dia, N)
        col[c] = u_dia[r+c] = l_dia[r-c+N] = 0

def solve(N):
    col = [0] * N
    u_dia = [0] * (2*N)
    l_dia = [0] * (2*N)
    dfs(0, col, u_dia, l_dia, N)

T = int(input())
for test_case in range(1, T + 1):
    N = int(input())
    answer = 0
    solve(N)
    print(f"#{test_case} {answer}")
