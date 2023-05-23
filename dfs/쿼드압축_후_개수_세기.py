def dfs(n, x, y, arr):
    if n == 1:
        return
    for i in range(x, x+n):
        for j in range(y, y+n):
            if arr[x][y] != arr[i][j]:
                nn = n//2
                dfs(nn, x, y, arr)
                dfs(nn, x, y+nn, arr)
                dfs(nn, x+nn, y, arr)
                dfs(nn, x+nn, y+nn, arr)
                return
    for i in range(x, x+n):
        for j in range(y, y+n):
            if i == x and j == y: continue
            arr[i][j] = -1
    
def solution(arr):
    N = len(arr)
    dfs(N, 0, 0, arr)
    answer = [0,0]
    for a in arr:
        for i in range(N):
            if a[i] == 0:
                answer[0] += 1
            elif a[i] == 1:
                answer[1] += 1
    return answer
