def dfs(n, x, y, arr, answer):
    if n == 1:
        answer[arr[x][y]] += 1
        return
    for i in range(x, x+n):
        for j in range(y, y+n):
            if arr[x][y] != arr[i][j]:
                nn = n//2
                dfs(nn, x, y, arr, answer)
                dfs(nn, x, y+nn, arr, answer)
                dfs(nn, x+nn, y, arr, answer)
                dfs(nn, x+nn, y+nn, arr, answer)
                return
    answer[arr[x][y]] += 1
    
def solution(arr):
    answer = [0,0]
    N = len(arr)
    dfs(N, 0, 0, arr, answer)
    return answer
