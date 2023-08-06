N, M, K = map(int, input().split())
data = [list(map(int, input().split())) for _ in range(N)]

ans = -int(1e9)
visited = [[False] * M for _ in range(N)]

dx = [-1, 1, 0, 0]
dy = [0, 0, 1, -1]

def is_visited(x, y):
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0 <= nx < N and 0 <= ny < M:
            if visited[nx][ny]:
                return True
    return False

def dfs(r, c, depth, total):
    global ans
    if depth == K:
        ans = max(ans, total)
        return
    for x in range(r, N):
        for y in range(c if x == r else 0, M):
            if visited[x][y]:
                continue
            if not is_visited(x, y):
                visited[x][y] = True
                dfs(x, y, depth+1, total+data[x][y])
                visited[x][y] = False

dfs(0, 0, 0, 0)
print(ans)
