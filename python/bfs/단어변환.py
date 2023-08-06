from collections import deque
def is_changable(w1, w2):
    cnt = 0
    for i in range(len(w1)):
        if w1[i] != w2[i]:
            cnt += 1
        if cnt > 1:
            return False
    return cnt == 1
        
def bfs(begin, target, words):
    visited = [0] * len(words)
    q = deque()
    q.append((begin, 0))
    while q:
        cur, dist = q.popleft()
        if cur == target:
            return dist
        for i, word in enumerate(words):
            if not visited[i] and is_changable(word, cur):
                visited[i] = 1
                q.append((word, dist+1))
    return 0
                
def solution(begin, target, words):
    answer = bfs(begin, target, words)
    return answer
