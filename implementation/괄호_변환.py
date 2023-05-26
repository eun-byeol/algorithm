from collections import deque
def divide(p):
    op, cl = 0, 0    
    for i, v in enumerate(p):
        if v == '(':
            op += 1
        else:
            cl += 1
        if op == cl:
            return p[:i+1], p[i+1:]
    return p, ""

def is_valid(u):
    st = deque([])
    for v in u:
        if v == ')':
            if not st:
                return False
            st.pop()
        else:
            st.append(v)
    return not st

def reverse(u):
    s = ""
    for v in u:
        if v == '(':
            s += ')'
        else:
            s += '('
    return s
            
def solve(p):
    if p == "":
        return ""
    u, v = divide(p)
    if is_valid(u):
        return u + solve(v)
    return '(' + solve(v) + ')' + reverse(u[1:-1])

def solution(p):
    answer = solve(p)
    return answer
