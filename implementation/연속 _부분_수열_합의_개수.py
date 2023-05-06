def solution(elements):
    N = len(elements)
    sum_v = set(elements)
    for n in range(2, N):
        for i in range(N):
            v = sum(elements[i:i+n])
            if i+n > N:
                v += sum(elements[:(i+n)%N])
            sum_v.add(v)
    sum_v.add(sum(elements))
    return len(sum_v)
