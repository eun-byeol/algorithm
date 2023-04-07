def solution(citations):
    citations.sort()
    length = len(citations)
    for i, c in enumerate(citations):
        cnt = length - i
        if c >= cnt:
            return cnt
    return 0
