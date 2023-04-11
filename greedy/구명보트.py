from collections import deque
def solution(people, limit):
    answer = 0
    left = deque(sorted(people))
    while len(left) > 1:
        small = left[0]
        big = left[-1]
        if limit - small - big >= 0:
            left.popleft()
        left.pop()
        answer += 1
    if left:
        answer += 1
    return answer
