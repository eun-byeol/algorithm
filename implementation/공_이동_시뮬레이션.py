def solution(n, m, x, y, queries):
    x1, y1, x2, y2 = x, y, x, y
    for cmd, dx in queries[::-1]:
        if cmd == 0:
            y2 = min(y2 + dx, m-1)
            if y1 != 0:
                y1 += dx
        elif cmd == 1:
            y1 = max(y1 - dx, 0)
            if y2 != m-1:
                y2 -= dx
        elif cmd == 2:
            x2 = min(x2 + dx, n-1)
            if x1 != 0:
                x1 += dx
        else: # cmd == 3
            x1 = max(x1 - dx, 0)
            if x2 != n-1:
                x2 -= dx
        if x2 < 0 or x1 >= n or y2 < 0 or y1 >= m:
            return 0
    return (x2-x1+1) * (y2-y1+1)
