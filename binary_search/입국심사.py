answer = 0
def count_people(minutes, times):
    cnt = 0
    for t in times:
        cnt += minutes // t
    return cnt

def find_time(start, end, times, n):
    global answer
    mid = (start + end) // 2
    cnt = count_people(mid, times)
    if start == end:
        answer = mid
        return
    if cnt >= n:
        find_time(start, mid, times, n)
    else:
        find_time(mid+1, end, times, n)

def solution(n, times):
    find_time(1, n * min(times), times, n)
    return answer
