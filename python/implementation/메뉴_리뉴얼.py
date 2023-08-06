from collections import defaultdict
from itertools import combinations

def solve(orders, size):
    union = defaultdict(int)
    for order in orders:
        if size > len(order):
            break
        for combi in combinations(sorted(order), size):
            union["".join(combi)] += 1
    data = list(zip(union.values(), union.keys()))
    data.sort(reverse=True)
    return [x[1] for x in data if x[0] > 1 and data[0][0] == x[0]]

def solution(orders, course):
    answer = []
    orders.sort(key = lambda x : -len(x))
    for size in course:
        answer.extend(solve(orders, size))
    answer.sort()
    return answer
