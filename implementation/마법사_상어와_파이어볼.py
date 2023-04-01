import sys
input = sys.stdin.readline

N, M, k = map(int, input().split())
graph = [[[] for _ in range(N)] for _ in range(N)]
fireballs = []
for _ in range(M):
    r, c, m, s, d = map(int, input().split())
    fireballs.append([r, c, m, s, d])

def move(graph, fireballs):
    while fireballs:
        r, c, m, s, d = fireballs.pop()
        nr = (N + r + (dr[d]*s)%N) % N
        nc = (N + c + (dc[d]*s)%N) % N
        graph[nr][nc].append([m, s, d])

def divide(graph, fireballs):
    for r in range(N):
        for c in range(N):
            cnt = len(graph[r][c])
            if cnt == 1:
                fireballs.append([r, c] + graph[r][c].pop())
            elif cnt > 1:
                sum_of_m, sum_of_s = 0, 0
                dir_flag = [0, 0] #홀짝
                while graph[r][c]:
                    m, s, d = graph[r][c].pop()
                    sum_of_m += m
                    sum_of_s += s
                    if d % 2 == 0:
                        dir_flag[1] = 1
                    else:
                        dir_flag[0] = 1
                next_m = int(sum_of_m / 5)
                if next_m == 0:
                    continue
                next_s = int(sum_of_s / cnt)
                if sum(dir_flag) == 1:
                    next_d = [0, 2, 4, 6]
                else:
                    next_d = [1, 3, 5, 7]
                for nd in next_d:
                    fireballs.append([r, c, next_m, next_s, nd])
        
dr = [-1, -1, 0, 1, 1, 1, 0, -1]
dc = [0, 1, 1, 1, 0, -1, -1, -1]

for _ in range(k):
    if len(fireballs) == 0:
        break
    move(graph, fireballs)
    divide(graph, fireballs)

result = sum([f[2] for f in fireballs])
print(result)
