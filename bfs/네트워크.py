from collections import deque
def bfs(i, computers, visited):
    q = deque([i])
    visited[i] = 1
    while q:
        i = q.popleft()
        for j, is_connected in enumerate(computers[i]):
            if is_connected and not visited[j]:
                q.append(j)
                visited[j] = 1

def solution(n, computers):
    answer = 0
    visited = [0] * n
    for i in range(0, n):
        if visited[i]: 
            continue
        bfs(i, computers, visited)
        answer += 1
    return answer
