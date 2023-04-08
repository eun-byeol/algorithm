def solution(brown, yellow):
    for r in range(3, brown//2):
        c = (brown - 2*r) // 2 + 2
        if c > r:
            continue
        if (r-2) * (c-2) == yellow:
            return [r, c]
    return []
