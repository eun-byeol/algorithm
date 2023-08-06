from itertools import permutations

def make_circle(weak, n):
    rst = [i for i in weak]
    for w in weak:
        rst.append(n + w)
    return rst

def simul(dist, weak, W, D):
    pre = weak[0]
    cnt = 1
    d = 0
    w = 1
    while d < D and w < W:
        if weak[w] - pre > dist[d]:
            d += 1
            pre = weak[w]
            cnt += 1
        w += 1
    if d < D and w == W:
        return cnt
    return -1
    
def solution(n, weak, dist):
    INF = int(1e9)
    answer = INF
    circle = make_circle(weak, n)
    W = len(weak)
    D = len(dist)
    for i in range(W):
        for permu in permutations(dist, D):
            cnt = simul(list(permu), circle[i:i+W], W, D)
            if cnt != -1 and cnt < answer:
                answer = cnt
    if answer == INF:
        return -1
    return answer
