def is_valid(i, j, history):
    for x, y in history:
        if abs(x - i) + abs(y - j) == 1:
            return False
    return True

def dfs(depth, total, history, r, c, N, M, K, data):
    global ans
    his = [h for h in history]
    if depth == K:
        ans = max(ans, total)
        return
    for i in range(r, N):
        for j in range(M):
            if i == r and j <= c:
                continue
            if not is_valid(i, j, his):
                continue
            his.append((i, j))
            dfs(depth + 1, total + data[i][j], his, i, j, N, M, K, data)
            his.pop()

def solve():
    global ans
    N, M, K = map(int, input().split())
    data = []
    for _ in range(N):
        data.append(list(map(int, input().split())))
    ans = -int(1e9)

    for i in range(N):
        for j in range(M):
            dfs(1, data[i][j], [(i,j)], i, j, N, M, K, data)
    print(ans)

solve()
