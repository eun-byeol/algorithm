dr = [1, 0, -1] # 아래, 오른, 왼위
dc = [0, 1, -1]
    
def solution(n):
    answer = []
    data = [[0] * n for _ in range(n)]
    max_cnt = sum(range(1, n+1))
    r, c, d = -1, 0, 0
    cnt = 1
    while cnt <= max_cnt:
        nr = r + dr[d]
        nc = c + dc[d]
        if nr < 0 or nr >= n or nc < 0 or nc >= n or data[nr][nc] != 0:
            d = (d+1) % 3
            continue
        data[nr][nc] = cnt
        cnt += 1
        r = nr
        c = nc
    for num in data:
        for i in range(n):
            if num[i] == 0:
                break
            answer.append(num[i])
    return answer
