def set_parent(user_idx, enroll, referral, N):
    info = [0] * (N+1)
    for child, parent in zip(enroll, referral):
        c_i = user_idx[child]
        p_i = user_idx.get(parent, 0)
        info[c_i] = p_i
    info[0] = -1
    return info

def calculate(c_i, total, parents, answer):
    if c_i == 0:
        return
    up_in = int(total * 0.1)
    if up_in == 0:
        answer[c_i] += total
        return
    
    p_i = parents[c_i]
    answer[c_i] += total - up_in
    calculate(p_i, up_in, parents, answer)

def solution(enroll, referral, seller, amount):
    N = len(enroll)
    answer = [0] * (N+1)
    user_idx = dict(zip(enroll, range(1, N+1)))
    parents = set_parent(user_idx, enroll, referral, N)
    for s, a in zip(seller, amount):
        c_i = user_idx[s]
        calculate(c_i, a * 100, parents, answer)
    return answer[1:]
