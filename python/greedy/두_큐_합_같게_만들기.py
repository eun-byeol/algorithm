from collections import deque

def solve(q1, q2, n):
    cnt = 0
    sum1 = sum(q1)
    sum2 = sum(q2)
    
    for _ in range(n * 4):
        if sum1 == sum2:
            return cnt
        elif sum1 > sum2:
            num = q1.popleft()
            q2.append(num)
            sum1 -= num
            sum2 += num
        else:
            num = q2.popleft()
            q1.append(num)
            sum2 -= num
            sum1 += num
        cnt += 1
    return -1
        
def solution(queue1, queue2):
    q1 = deque(queue1)
    q2 = deque(queue2)
    N = len(queue1)
    return solve(q1, q2, N)
