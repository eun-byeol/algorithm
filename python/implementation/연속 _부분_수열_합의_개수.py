def solution(elements):
    N = len(elements)
    sum_v = set()
    
    for i in range(N):
        v = elements[i]
        sum_v.add(v)
        for j in range(i+1, i+N):
            v += elements[j%N]
            sum_v.add(v)
    return len(sum_v)
