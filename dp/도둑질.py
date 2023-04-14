def find_dp(money):
    dp = []
    for i, m in enumerate(money):
        if i == 0:
            dp.append(m)
        elif i == 1:
            dp.append(max(dp[i-1], m))
        else:
            dp.append(max(dp[i-1], dp[i-2] + m))
    return dp[-1]

def solution(money):
    answer = max(find_dp(money[1:]), find_dp(money[:-1]))
    return answer
