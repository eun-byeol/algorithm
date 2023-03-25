import sys
input = sys.stdin.readline

N, M, k = map(int, input().split())
data = [list(map(int, input().split())) for _ in range(N)]
shark = [[] for _ in range(M+1)]
for i in range(N):
    for j in range(N):
        if data[i][j] > 0:
            shark[data[i][j]].extend([i, j])
data = list(map(int, input().split()))
for i in range(1, M+1):
    shark[i].append(data[i-1])

direction = {}
for i in range(1, M+1):
    data = [list(map(int, input().split())) for _ in range(4)]
    direction[i] = data

def update_position(num, shark, graph, time):
    x, y, d = shark[num]
    d_info = direction[num][d-1]
    # print(num, x, y, d, d_info)
    for idx in d_info:
        nx = x + dr[idx]
        ny = y + dc[idx]
        if nx < 0 or nx >= N or ny < 0 or ny >= N: continue
        if graph[nx][ny][0] == -1:
            shark[num][0], shark[num][1], shark[num][2] = nx, ny, idx #위치정보 갱신
            # print("next=", nx, ny, idx)
            return True
        if time - graph[nx][ny][0] >= k: # 냄새가 사라진 경우
            shark[num][0], shark[num][1], shark[num][2] = nx, ny, idx #위치정보 갱신
            # print("냄새X next=", nx, ny, idx)
            return True
    # 빈칸이 없었으면
    for idx in d_info:
        nx = x + dr[idx]
        ny = y + dc[idx]
        if nx < 0 or nx >= N or ny < 0 or ny >= N: continue
        if graph[nx][ny][1] == num:
            shark[num][0], shark[num][1], shark[num][2] = nx, ny, idx
            # print("노 빈칸 next=", nx, ny, idx)
            return True
    return False  


dr = [0, -1, 1, 0, 0] #위, 아래, 왼쪽, 오른쪽
dc = [0, 0, 0, -1, 1]

graph = [[[-1,-1] for _ in range(N)] for _ in range(N)]

# for g in graph:
#     print(g)

count = M
result = -1
for time in range(1001):
    for num in range(1, M+1):
        x, y, d = shark[num]
        if x == -1: 
            continue
        if graph[x][y][0] == time:
            if graph[x][y][1] < num:
                shark[num][0] = -1 # 현재 상어 제거
            else:
                shark[graph[x][y]][0] = -1 # 기존 상어 제거
                graph[x][y][1] = num
            count -= 1
            if count == 1: 
                break
        else:
            graph[x][y][0], graph[x][y][1] = time, num
        if shark[num][0] == -1: 
            continue
        update_position(num, shark, graph, time)
    if count == 1:
        result = time
        break
    # for g in graph:
    #     print(g)
    # print("time = ", time, "\n----------------")
print(result)
