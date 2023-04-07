from bisect import bisect_left
def solution(citations):
    answer = 0
    citations.sort()
    length = len(citations)
    for h in range(citations[-1], -1, -1):
        if length - bisect_left(citations, h) >= h:
            answer = h
            break
    return answer
