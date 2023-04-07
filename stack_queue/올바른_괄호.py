from collections import deque
def solution(s):
    stack = deque([])
    for p in s:
        if p == '(':
            stack.append(1)
        else:
            if len(stack) == 0:
                return False
            stack.pop()
    return len(stack) == 0
