def solution(prices):
    N = len(prices)
    answer = [0] * N
    st = []
    for i, cur in enumerate(prices):
        while st:
            if st[-1][0] <= cur:
                break
            pre, j = st.pop()
            answer[j] = i - j
        st.append((cur, i))
    while st:
        num, i = st.pop()
        answer[i] = N-i-1
    return answer
