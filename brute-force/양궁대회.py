from itertools import combinations_with_replacement
M = 11

def is_valid(score, answer):
    for i in range(M-1, -1, -1):
        if score[i] > answer[i]:
            return True
        elif score[i] < answer[i]:
            return False
    return False

def solution(n, info):
    answer = [-1]
    max_diff = -1
    for combi in combinations_with_replacement(range(M), n):
        score = [0] * M
        for i in combi:
            score[i] += 1
        
        lion = 0
        apeach = 0
        for i in range(M):
            if score[i] == info[i] == 0:
                continue
            elif score[i] > info[i]:
                lion += 10-i
            else:
                apeach += 10-i
        
        if lion > apeach:
            diff = lion - apeach
            if diff > max_diff:
                max_diff = diff
                answer = score
            elif diff == max_diff:
                if is_valid(score, answer):
                    max_diff = diff
                    answer = score
    return answer
