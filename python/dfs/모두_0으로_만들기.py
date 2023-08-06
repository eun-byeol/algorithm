import sys
sys.setrecursionlimit(300000)

def make_tree(edges, N):
    tree = [[] for _ in range(N)]
    for u, v in edges:
        tree[u].append(v)
        tree[v].append(u)
    return tree

def dfs(v, visited, a, tree):
    global answer
    for nxt in tree[v]:
        if visited[nxt] == 0:
            visited[nxt] = 1
            a[v] += dfs(nxt, visited, a, tree)
    answer += abs(a[v])
    return a[v]
            
def solution(a, edges):
    global answer
    answer = 0
    N = len(a)
    tree = make_tree(edges, N)
    visited = [0] * N
    visited[0] = 1
    result = dfs(0, visited, a, tree)
    if result != 0:
        return -1
    return answer
