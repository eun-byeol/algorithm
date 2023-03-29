import sys
from collections import deque
input = sys.stdin.readline
INF = 1e9

N, M, gas = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(N)]
taxi_x, taxi_y = map(int, input().split())
taxi_x -= 1
taxi_y -= 1
destination = [0, 0]
for i in range(M):
    x, y, a, b = map(int, input().split())
    graph[x-1][y-1] = i+2 #2부터 시작
    destination.append((a-1, b-1))

def find_customer(x, y):
    min_dist = INF
    customer = []
    visited = [[0] * N for _ in range(N)]
    q = deque([ (x, y, 0) ])
    while q:
        r, c, dist = q.popleft()
        visited[r][c] = 1
        if dist > min_dist:
            break
        if graph[r][c] > 1:
            min_dist = dist
            customer.append((r, c, dist))
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
            if nr < 0 or nr >= N or nc < 0 or nc >= N or visited[nr][nc]:
                continue
            if graph[nr][nc] != 1:
                q.append((nr, nc, dist + 1))
                visited[nr][nc] = 1
    if len(customer) == 0:
        return -1, -1, -1
    customer.sort(key = lambda x: (x[0], x[1]))
    return customer[0]

def drive(x, y, cust_num):
    visited = [[0] * N for _ in range(N)]
    q = deque([ (x, y, 0) ])
    visited[x][y] = 1
    while q:
        r, c, dist = q.popleft()
        if (r, c) == destination[cust_num]:
            return r, c, dist
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
            if nr < 0 or nr >= N or nc < 0 or nc >= N or visited[nr][nc]:
                continue
            if graph[nr][nc] != 1:
                q.append((nr, nc, dist + 1))
                visited[nr][nc] = 1
    return -1, -1, -1

dr = [1, 0, -1, 0]
dc = [0, 1, 0, -1]

min_dist = INF

for _ in range(M):
    taxi_x, taxi_y, cus_dist = find_customer(taxi_x, taxi_y)
    if taxi_x == -1:
        gas = -1
        break
    cus_num = graph[taxi_x][taxi_y]
    graph[taxi_x][taxi_y] = 0
    gas -= cus_dist
    if gas <= 0:
        gas = -1
        break
    taxi_x, taxi_y, dist = drive(taxi_x, taxi_y, cus_num)
    gas -= dist
    if gas < 0:
        gas = -1
        break
    gas += dist*2
print(gas)
