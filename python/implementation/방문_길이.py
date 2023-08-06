def solution(dirs):
    visited = set()
    dr = {"U": (-1, 0), "D": (1, 0), "R": (0, 1), "L": (0, -1)}
    r, c = 0, 0
    for d in dirs:
        nr = r + dr[d][0]
        nc = c + dr[d][1]
        if -5 <= nr <= 5 and -5 <= nc <= 5:
            visited.add((r, c, nr, nc)) # (r,c)->(nr,nc)
            visited.add((nr, nc, r, c)) # (r,c)<-(nr,nc)
            r, c = nr, nc
    answer = len(visited) // 2
    return answer
