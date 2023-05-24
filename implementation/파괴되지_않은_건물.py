def solution(board, skill):
    answer = 0
    N = len(board)
    M = len(board[0])
    prefix_sum = [[0] * (M+1) for _ in range(N+1)]
    
    for t, r1, c1, r2, c2, d in skill:
        if t == 1:
            d *= -1
        prefix_sum[r1][c1] += d
        prefix_sum[r2+1][c2+1] += d
        prefix_sum[r1][c2+1] += -d
        prefix_sum[r2+1][c1] += -d
        
    for i in range(N):
        for j in range(M):
            prefix_sum[i][j+1] += prefix_sum[i][j]

    for j in range(M):
        for i in range(N):
            prefix_sum[i+1][j] += prefix_sum[i][j]

    for i in range(N):
        for j in range(M):
            board[i][j] += prefix_sum[i][j]
            if board[i][j] > 0:
                answer += 1
    return answer
