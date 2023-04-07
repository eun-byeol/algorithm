def solution(clothes):
    cnt = {}
    for name, kind in clothes:
        cnt[kind] = cnt.get(kind, 0) + 1
    answer = 1
    for key in cnt:
        answer *= (cnt[key] + 1)
    return answer - 1
