INF = int(1e9)

def solution(alp, cop, problems):
    max_alp, max_cop = 0, 0
    for problem in problems:
        max_alp = max(problem[0], max_alp)
        max_cop = max(problem[1], max_cop)
    
    alp = min(alp, max_alp)
    cop = min(cop, max_cop)
    dp = [[INF] * (max_cop+1) for _ in range(max_alp+1)]
    dp[alp][cop] = 0
    for i in range(alp, max_alp+1):
        for j in range(cop, max_cop+1):
            if i < max_alp:
                dp[i+1][j] = min(dp[i][j]+1, dp[i+1][j])
            if j < max_cop:
                dp[i][j+1] = min(dp[i][j]+1, dp[i][j+1])
            
            for alp_req, col_req, alp_rwd, cop_rwd, cost in problems:
                if i >= alp_req and j >= col_req:
                    nxt_alp = min(i+alp_rwd, max_alp)
                    nxt_cop = min(j+cop_rwd, max_cop)
                    dp[nxt_alp][nxt_cop] = min(dp[i][j]+cost, dp[nxt_alp][nxt_cop])
    return dp[max_alp][max_cop]
