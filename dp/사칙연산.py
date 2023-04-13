def solution(arr):
    total_max, total_min = 0, 0
    sum = 0
    for i in range(len(arr)-1, -1, -1):
        if arr[i] == "+":
            continue
        if arr[i] == "-":
            pre_max, pre_min = total_max, total_min
            minus_next = int(arr[i+1])
            total_max = max(-(sum + pre_min), -(minus_next) + sum-minus_next + pre_max)
            total_min = min(-(sum + pre_max), -(sum) + pre_min)
            sum = 0
        else:
            sum += int(arr[i])
    answer = sum + total_max
    return answer
