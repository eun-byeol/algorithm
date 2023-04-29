import heapq
def solution(n, k, enemy):
    answer = 0
    max_heap = []
    
    i = 0
    while i < len(enemy):
        e = enemy[i]
        if k == 0 and n < e:
            break
        if n < e:
            if len(max_heap) > 0 and -max_heap[0] > e:
                n += -heapq.heappop(max_heap)
                i -= 1
            else:
                answer += 1
            k -= 1
        else:
            n -= e
            heapq.heappush(max_heap, -e)
            answer += 1
        i += 1
    return answer
