from collections import deque

def solution(want, number, discount):
    answer = 0
    wants = {}
    for i in range(len(want)):
        wants[want[i]] = number[i]
    buy = deque([])
    
    for product in discount: 
        buy.append(product)
        
        if product in wants:
            wants[product] -= 1
            
        if len(buy) == 10:
            answer += 1
            for v in wants.values():
                if v != 0:
                    answer -= 1
                    break
            rm_product = buy.popleft()
            if rm_product in wants:
                wants[rm_product] += 1
    return answer
