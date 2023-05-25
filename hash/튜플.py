import re

def make_data(s):
    data = []
    groups = re.split("{|}|,{", s[1:-1])
    for group in groups:
        if group == '':
            continue
        g = []
        for num in group.split(','):
            g.append(int(num))
        data.append(g)
    return data

def solve(data):
    result = []
    data.sort(key=lambda x : len(x))
    for group in data:
        result.extend(set(group) - set(result))
    return result
        
def solution(s):
    data = make_data(s)
    answer = solve(data)
    return answer
