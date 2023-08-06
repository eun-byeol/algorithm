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

# ver. 투포인터
def solution(people, limit):
    people.sort()
    start = 0
    end = len(people) - 1
    pair_num = 0
    while start < end:
        if people[start] + people[end] <= limit:
            start += 1
            pair_num += 1
        end -= 1
    return len(people) - pair_num
