# ver. 오름차순
def solution(citations):
    citations.sort()
    length = len(citations)
    for i, c in enumerate(citations):
        cnt = length - i
        if c >= cnt:
            return cnt
    return 0

# ver. 내림차순
def solution(citations):
    citations.sort(reverse=True)
    length = len(citations)
    for i, c in enumerate(citations):
        if i >= c:
            return i
    return length
