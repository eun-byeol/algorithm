from collections import Counter
def solution(clothes):
    cnt = Counter([kind for name, kind in clothes])
    answer = 1
    for key in cnt:
        answer *= (cnt[key] + 1)
    return answer - 1
