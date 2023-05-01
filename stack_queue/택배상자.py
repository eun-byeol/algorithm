def simul(order):
    cnt = 0
    N = len(order)
    st = []
    idx = 0
    n = 1
    while n <= N:
        if n == order[idx]:
            idx += 1
            cnt += 1
        elif st and st[-1] == order[idx]:
            idx += 1
            cnt += 1
            st.pop()
            continue
        else:
            st.append(n)
        n += 1
        
    while st:
        if st[-1] != order[idx]:
            break
        idx += 1
        cnt += 1
        st.pop()
    return cnt

def solution(order):
    answer = simul(order)
    return answer
