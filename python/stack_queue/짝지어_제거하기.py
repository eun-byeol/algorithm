def solution(s):
    answer = 0
    st = []
    
    for ss in s:
        if st and st[-1] == ss:
            st.pop()
            continue
        st.append(ss)
    if not st:
        answer = 1
    return answer
