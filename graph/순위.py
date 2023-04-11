# ver. bfs
from collections import deque
def bfs(idx, value, total, graph, n):
    visited = [0] * n
    q = deque([idx])
    visited[idx] = 1
    while q:
        cur = q.popleft()
        for nxt, is_value in enumerate(graph[cur]):
            if is_value == value and not visited[nxt]:
                q.append(nxt)
                visited[nxt] = 1
    total[idx] += sum(visited) -1

def solution(n, results):
    graph = [[0] * n for _ in range(n)]
    for w, l in results:
        graph[w-1][l-1] = 1
        graph[l-1][w-1] = -1
    total = [0] * n
    for i in range(n):
        bfs(i, 1, total, graph, n)
        bfs(i, -1, total, graph, n)
    return total.count(n-1)

# ver. set 딕셔너리
from collections import defaultdict
def solution(n, results):
    answer = 0
    win = defaultdict(set)
    lose = defaultdict(set)
    
    for w, l in results:
        win[w].add(l) #한 개 추가
        lose[l].add(w)
    
    for num in range(1, n+1):
        for w in lose[num]:
            win[w].update(win[num]) #여러개 추가
        for l in win[num]:
            lose[l].update(lose[num])
    
    for num in range(1, n+1):
        if len(win[num]) + len(lose[num]) == n-1:
            answer += 1
    return answer
