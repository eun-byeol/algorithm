def solution(s):
    N = len(s)
    answer = N
    for l in range(1, N//2 + 1):
        size, duplication = l, 1
        pre = s[:l]
        for i in range(l, N, l):
            if pre == s[i:i+l]:
                duplication += 1
                continue
            pre = s[i:i+l]
            size += len(pre)
            if duplication > 1:
                size += len(str(duplication))
                duplication = 1
        if duplication > 1:
            size += len(str(duplication))
        answer = min(size, answer)
    return answer
