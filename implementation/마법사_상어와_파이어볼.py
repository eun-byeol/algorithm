import sys
input = sys.stdin.readline

N, M, k = map(int, input().split())
graph = [[[] for _ in range(N)] for _ in range(N)]
for _ in range(M):
    r, c, m, s, d = map(int, input().split())
    graph[r-1][c-1].append([m, s, d])

def move(graph):
    tmp = [[[] for _ in range(N)] for _ in range(N)]
    for r in range(N):
        for c in range(N):
            for m, s, d in graph[r][c]:
                nr = (N + r + (dr[d]*s)%N) % N
                nc = (N + c + (dc[d]*s)%N) % N
                tmp[nr][nc].append([m, s, d])
    return tmp

def divide(graph, count):
    for r in range(N):
        for c in range(N):
            cnt = len(graph[r][c])
            if cnt <= 1:
                continue
            sum_of_m = 0
            sum_of_s = 0
            dir_flag = [0, 0] #홀, 짝
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
                count -= cnt
                continue
            count += 4 - cnt
            next_s = int(sum_of_s / cnt)
            if sum(dir_flag) == 1:
                next_d = [0, 2, 4, 6]
            else:
                next_d = [1, 3, 5, 7]
            for i in range(4):
                graph[r][c].append([next_m, next_s, next_d[i]])
    return count
    
dr = [-1, -1, 0, 1, 1, 1, 0, -1]
dc = [0, 1, 1, 1, 0, -1, -1, -1]

count = M
for _ in range(k):
    if count == 0:
        break
    graph = move(graph)
    count = divide(graph, count)

result = 0
if count != 0:
    for r in range(N):
        for c in range(N):
            for fireball in graph[r][c]:
                result += fireball[0]
print(result)
