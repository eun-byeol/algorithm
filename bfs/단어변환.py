from collections import defaultdict, deque
def is_changable(w1, w2):
    cnt = 0
    for i in range(len(w1)):
        if w1[i] != w2[i]:
            cnt += 1
    return cnt == 1

def bfs(begin, target, graph):
    q = deque([(begin, 0)])
    visited = [begin]
    while q:
        cur, dist = q.popleft()
        if cur == target:
            return dist

        for nxt in graph[cur]:
            if nxt not in visited:
                q.append((nxt, dist+1))
                visited.append(nxt)
    return 0

def solution(begin, target, words):
    graph = defaultdict(set)
    for w1 in words:
        for w2 in words:
            if is_changable(w1, w2):
                graph[w1].add(w2)
        if is_changable(begin, w1):
            graph[begin].add(w1)
    return bfs(begin, target, graph)
