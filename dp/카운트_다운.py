def solve(t):
    cnt = [0] * (t+1)
    sb = [0] * (t+1)
    if t <= 20:
        return [1, 1]
    
    for n in range(1, 21):
        cnt[n] = 1
        sb[n] = 1
    for n in range(21, t+1):
        # single
        cnt[n] = cnt[20] + cnt[n - 20]
        sb[n] = 1 + sb[n - 20]
        # double
        D = 1 + cnt[n - (n//2) * 2]
        Dsb = sb[n - (n//2) * 2]
        if n >= 40:
            D = 1 + cnt[n - 40]
            Dsb = sb[n - 40]
        if cnt[n] > D:
            cnt[n] = D
            sb[n] = Dsb
        # triple
        T = 1 + cnt[n - (n//3) * 3]
        Tsb = sb[n - (n//3) * 3]
        if n >= 60:
            T = 1 + cnt[n - 60]
            Tsb = sb[n - 60]
        if cnt[n] > T:
            cnt[n] = T
            sb[n] = Tsb
        # bool
        if n >= 50:
            B = 1 + cnt[n-50]
            Bsb = 1 + sb[n-50]
            if cnt[n] > B or (cnt[n] == B and sb[n] < Bsb):
                cnt[n] = B
                sb[n] = Bsb
    return [cnt[t], sb[t]]
        
def solution(target):
    return solve(target)
