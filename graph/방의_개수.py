from collections import defaultdict
def solution(arrows):
    answer = 0
    dr = [-1, -1, 0, 1, 1, 1, 0, -1]
    dc = [0, 1, 1, 1, 0, -1, -1, -1]
    visited = defaultdict(set)
    
    r, c = 0, 0
    for i in arrows:
        for _ in range(2):
            nr = r + dr[i]
            nc = c + dc[i]
            if (nr,nc) in visited and (r,c) not in visited[(nr,nc)]:
                answer += 1
            visited[(nr,nc)].add((r,c))
            visited[(r,c)].add((nr,nc))
            r = nr
            c = nc
    return answer
