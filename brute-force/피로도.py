from itertools import permutations
def solution(k, dungeons):
    answer = 0
    for permu in permutations(dungeons, len(dungeons)):
        energy = k
        cnt = 0
        for before, after in permu:
            if energy >= before:
                cnt += 1
                energy -= after
            else:
                break
        answer = max(answer, cnt)
    return answer

# ver. dfs 백트래킹
answer = 0
def dfs(cnt, k, visited, dungeons, N):
    global answer
    for i in range(N):
        before, after = dungeons[i]
        if k >= before and not visited[i]:
            visited[i] = 1
            dfs(cnt+1, k-after, visited, dungeons, N)
            visited[i] = 0
    answer = max(answer, cnt)
    
def solution(k, dungeons):
    N = len(dungeons)
    visited = [0] * N
    dfs(0, k, visited, dungeons, N)
    return answer
