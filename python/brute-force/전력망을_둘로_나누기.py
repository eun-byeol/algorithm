from collections import deque
def bfs(tree, n):
    visited = [0] * n
    q = deque([0])
    visited[0] = 1
    while q:
        v = q.popleft()
        for nv, is_connected in enumerate(tree[v]):
            if is_connected == 1 and not visited[nv]:
                q.append(nv)
                visited[nv] = 1
    return abs(2 * sum(visited) - n)

def set_tree(tree, v1, v2, value):
    tree[v1-1][v2-1] = value
    tree[v2-1][v1-1] = value

def solution(n, wires):
    answer = int(1e9)
    tree = [[0] * n for _ in range(n)]
    for v1, v2 in wires:
        set_tree(tree, v1, v2, 1)
    for v1, v2 in wires:
        set_tree(tree, v1, v2, 0)
        answer = min(answer, bfs(tree, n))
        set_tree(tree, v1, v2, 1)
    return answer
