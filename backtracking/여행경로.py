from collections import defaultdict
answer = []

def dfs(cnt, visited, cities, n):
    global answer
    if cnt == n:
        if len(answer) == 0:
            answer = visited[:]
        return
    dep = visited[-1]
    for i, arri in enumerate(cities[dep]):
        if arri == "": continue
        cities[dep][i] = ""
        visited.append(arri)
        dfs(cnt+1, visited, cities, n)
        cities[dep][i] = arri
        visited.pop()

def solution(tickets):
    n = len(tickets)
    cities = defaultdict(list)
    for departure, arrival in tickets:
        cities[departure].append(arrival)
    for c in cities:
        cities[c].sort()
    dfs(0, ["ICN"], cities, n)
    return answer
