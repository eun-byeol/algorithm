INF = int(1e9)
dr = [0, -1, -1, -1]
dc = [-1, -1, 0, 1]
def solve(graph, N):
    dist = [[INF] * 3 for _ in range(N)]
    dist[0][1] = graph[0][1]
    dist[0][2] = graph[0][1] + graph[0][2]
    for r in range(1, N):
        for c in range(3):
            for i in range(4):
                pr = r + dr[i]
                pc = c + dc[i]
                if pr < 0 or pr >= N or pc < 0 or pc >= 3:
                    continue
                if dist[r][c] > dist[pr][pc] + graph[r][c]:
                    dist[r][c] = dist[pr][pc] + graph[r][c]
    return dist[N - 1][1]

tc = 0
while True:
    tc += 1
    N = int(input())
    graph = []
    if N == 0:
        break
    for _ in range(N):
        line = list(map(int, input().split()))
        graph.append(line)
    ans = solve(graph, N)
    print(f"{tc}. {ans}")
