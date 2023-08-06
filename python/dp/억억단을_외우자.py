def set_divisor(e, starts, min_s):
    divisor = [0] * (e+1)
    for i in range(1, int(e**0.5)+1):
        divisor[i*i] += 1
        for j in range(i*(i+1), e+1, i):
            divisor[j] += 2
    return divisor

def set_dp(e, divisor):
    dp = [0] * (e+1)
    max_v, pre = 0, 0
    for i in range(e, 0, -1):
        if (max_v <= divisor[i]):
            dp[i] = i
            pre = i
            max_v = divisor[i]
        else:
            dp[i] = pre
    return dp

def solution(e, starts):
    answer = []
    min_s = min(starts)
    
    divisor = set_divisor(e, starts, min_s)
    dp = set_dp(e, divisor)
    
    for s in starts:
        answer.append(dp[s])
    return answer
