from collections import deque
pairs = {')':'(', '}':'{', ']':'['}
def is_valid(q):
    st = deque()
    for op in q:
        if op == '(' or op == '{' or op == '[':
            st.append(op)
        else:
            if len(st) == 0 or pairs[op] != st.pop():
                return False
    if st:
        return False
    return True

def solution(s):
    answer = 0
    q = deque(list(s))
    
    if is_valid(q):
        answer += 1
    for _ in range(len(s)-1):
        q.rotate(-1)
        if is_valid(q):
            answer += 1
    return answer
