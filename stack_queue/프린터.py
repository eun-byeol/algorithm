from collections import deque
def solution(priorities, location):
    answer = 0
    q = deque([])
    for i, p in enumerate(priorities):
        if i == location:
            q.append((p, 1))
        else:
            q.append((p, 0))

    while q:
        n = len(q)
        p, flag = q.popleft()
        for np, nf in q:
            if p < np:
                q.append((p, flag))
                break
        if len(q) == n:
            continue
        answer += 1
        if flag == 1:
            break
    return answer
