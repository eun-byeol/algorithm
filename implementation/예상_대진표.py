import math
def go(L, mid, r, a, b):
    global answer
    if r == 1:
        answer = r
        return
    if a <= mid and b <= mid:
        go(L//2, mid - L//2, r-1, a, b)
    elif a > mid and b > mid:
        go(L//2, mid + L//2, r-1, a, b)
    else:
        answer = r
        return 

def solution(n,a,b):
    global answer
    answer = 0
    go(n//2, n//2, int(math.log2(n)), a, b)
    return answer
