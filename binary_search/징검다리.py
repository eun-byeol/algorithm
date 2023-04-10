def solution(distance, rocks, n):
    rocks.sort()
    answer = 0
    start, end = 1, distance

    while start <= end:
        mid = (start + end) // 2
        del_cnt = 0
        pre_rock = 0
        for rock in rocks:
            if rock - pre_rock < mid:
                del_cnt += 1
            else:
                pre_rock = rock
            if del_cnt > n:
                break
        if del_cnt > n:
            end = mid - 1
        else:
            answer = mid
            start = mid + 1
    return answer
