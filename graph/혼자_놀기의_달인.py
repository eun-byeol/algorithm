def count_group(idx, visited, cards):
    cnt = 0
    while True:
        next_i = cards[idx]-1
        if visited[next_i]:
            break
        visited[next_i] = 1
        cnt += 1
        idx = next_i
    return cnt

def solution(cards):
    N = len(cards)
    cnts = []
    visited = [0] * N
    for i in range(N):
        if visited[i]:
            continue
        cnts.append(count_group(i, visited, cards))
        
    if len(cnts) == 1:
        return 0
    cnts.sort(key = lambda x : -x)
    return cnts[0] * cnts[1]
