import heapq
def solution(n, k, enemy):
    hp = enemy[:k]
    heapq.heapify(hp)
    
    for i in range(k, len(enemy)):
        n -= heapq.heappushpop(hp, enemy[i])
        if n < 0:
            return i
    return len(enemy)
