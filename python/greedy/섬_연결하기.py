def find(parents, v):
    if parents[v] != v:
        parents[v] = find(parents, parents[v])
    return parents[v]

def kruskal(n, costs):
    costs.sort(key = lambda x : x[2])
    parents = list(range(n))
    total = 0

    for v1, v2, e in costs:
        p1 = find(parents, v1)
        p2 = find(parents, v2)
        if p1 == p2:
            continue
        if p1 < p2:
            parents[p2] = p1
        else:
            parents[p1] = p2
        total += e
    return total

def solution(n, costs):
    answer = 0
    if len(costs[0]) == 0:
        return answer
    return kruskal(n, costs)
