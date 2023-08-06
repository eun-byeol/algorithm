def solution(triangle):
    for h in range(1, len(triangle)):
        pre = triangle[h-1]
        cur = triangle[h]
        for i in range(len(cur)):
            left = pre[i-1] if i > 0 else 0
            right = pre[i] if i < len(cur)-1 else 0
            cur[i] += max(left, right)
    return max(triangle[-1])
