from collections import deque

def is_valid(skill, tree):
    q = deque(list(skill))
    for c in tree:
        if c in skill:
            if q[0] == c:
                q.popleft()
            else:
                return False
    return True

def solution(skill, skill_trees):
    answer = 0
    for tree in skill_trees:
        if is_valid(skill, tree):
            answer += 1
    return answer
