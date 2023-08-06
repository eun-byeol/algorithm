from heapq import heapify, heappop, heappush
def solution(scoville, K):
    answer = 0
    heapify(scoville)
    while len(scoville) > 1:
        v1 = heappop(scoville)
        v2 = heappop(scoville)
        if v1 == v2 == 0:
            answer = -1
            break
        if v1 >= K:
            break
        heappush(scoville, v1 + v2 * 2)
        answer += 1
    if len(scoville) > 0 and scoville[0] < K:
        answer = -1
    return answer
