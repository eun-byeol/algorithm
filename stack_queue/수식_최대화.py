from itertools import permutations
from collections import deque
import re

def calculate(a, b, op):
    if op == "+":
        return a + b
    if op == "-":
        return a - b
    return a * b

def simul(nums, ops, pr):
    st_num = deque([])
    st_op = deque([])
    st_num.append(nums[0])
    i = 0
    while i < len(ops):
        if st_op and pr[ops[i]] >= pr[st_op[-1]]: # 추가되는 연산자 우선순위가 더 낮은 경우
            b = st_num.pop()
            a = st_num.pop()
            op = st_op.pop()
            st_num.append(calculate(a, b, op))
            continue
        st_op.append(ops[i])
        st_num.append(nums[i+1])
        i += 1
    while st_op:
        b = st_num.pop()
        a = st_num.pop()
        op = st_op.pop()
        st_num.append(calculate(a, b, op))
    return abs(st_num.pop())

def solution(expression):
    answer = 0
    op = ['+', '-', '*']
    pr = {}
    nums = list(map(int, re.split('[*+-]', expression)))
    ops = re.compile('[*+-]').findall(expression)
    for permu in permutations(op, 3):
        for k, v in zip(permu, range(1, 4)):
            pr[k] = v
        answer = max(simul(nums, ops, pr), answer)
    return answer
