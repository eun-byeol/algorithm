def is_valid(i, j, history):
    for x, y in history:
        if abs(x - i) + abs(y - j) == 1:
            return False
    return True

def dfs(depth, total, history, r, c):
    global ans
    his = [h for h in history]
    if depth == K:
        ans = max(ans, total)
        return
    for i in range(r, N):
        for j in range(c+1 if i == r else 0, M):
            if not is_valid(i, j, his):
                continue
            his.append((i, j))
            dfs(depth + 1, total + data[i][j], his, i, j)
            his.pop()

N, M, K = map(int, input().split())
data = [list(map(int, input().split())) for _ in range(N)]
ans = -int(1e9)

for i in range(N):
    for j in range(M):
        dfs(1, data[i][j], [(i, j)], i, j)
print(ans)
