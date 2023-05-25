def make_data(s):
    data = []
    groups = s[2:-2].split("},{")
    for group in groups:
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
