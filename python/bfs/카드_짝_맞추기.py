from itertools import permutations
from collections import defaultdict, deque
from copy import deepcopy

INF = 1e9

def set_pairs(board):
    pairs = defaultdict(list)
    for i in range(4):
        for j in range(4):
            if board[i][j] != 0:
                pairs[board[i][j]].append((i, j))
    return pairs

def bfs(r, c, tr, tc, board):
    dr = [0, 0, 1, -1]
    dc = [1, -1, 0, 0]
    dist = [[INF] * 4 for _ in range(4)]
    q = deque([(r, c)])
    dist[r][c] = 0
    while q:
        r, c = q.popleft()
        if r == tr and c == tc:
            return dist[r][c]
        
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
            if 0 <= nr < 4 and 0 <= nc < 4 and dist[nr][nc] > dist[r][c] + 1:
                dist[nr][nc] = dist[r][c] + 1
                q.append((nr, nc))
        
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
            while 0 <= nr + dr[i] < 4 and 0 <= nc + dc[i] < 4 and board[nr][nc] == 0:
                nr += dr[i]
                nc += dc[i]
            if 0 <= nr < 4 and 0 <= nc < 4 and dist[nr][nc] > dist[r][c] + 1:
                dist[nr][nc] = dist[r][c] + 1
                q.append((nr, nc))

def simul(orders, pairs, board, r, c):
    cnt = 0
    for num in orders:
        left, right = pairs[num]
        rst1 = bfs(r, c, left[0], left[1], board)
        rst2 = bfs(r, c, right[0], right[1], board)

        if rst1 < rst2:
            cnt += rst1
            cnt += bfs(left[0], left[1], right[0], right[1], board)
            r, c = right
        else:
            cnt += rst2
            cnt += bfs(right[0], right[1],left[0], left[1], board)
            r, c = left
        board[left[0]][left[1]] = 0
        board[right[0]][right[1]] = 0
        cnt += 2
    return cnt

def solution(board, r, c):
    answer = INF
    pairs = set_pairs(board)
    for permu in permutations(list(pairs), len(pairs)):
        cnt = simul(permu, pairs, deepcopy(board), r, c)
        answer = min(cnt, answer)
    return answer
