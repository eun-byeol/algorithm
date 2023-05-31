from itertools import combinations
def is_minimality(combi, visited):
    for v in visited:
        if len(v - set(combi)) == 0:
            return False
    return True

def is_uniqueness(combi, relation, N):
    data = set()
    for r in relation:
        value = []
        for i in combi:
            value.append(r[i])
        data.add(tuple(value))
    return len(data) == N

def solution(relation):
    answer = 0
    N = len(relation)
    M = len(relation[0])
    visited = []
    for num in range(1, M+1):
        for combi in combinations(range(M), num):
            if not is_minimality(combi, visited):
                continue
            if is_uniqueness(combi, relation, N):
                visited.append(set(combi))
                answer += 1
    return answer
