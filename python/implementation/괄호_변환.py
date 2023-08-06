from collections import deque, defaultdict
def divide(p):
    cnt = defaultdict(int)
    for i, v in enumerate(p):
        cnt[v] += 1
        if cnt['('] == cnt[')']:
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
        s += ')' if v == '(' else '('
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
