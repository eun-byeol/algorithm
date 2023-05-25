def make_graph(info, edges):
    graph = [[] for _ in range(len(info))]
    for parent, child in edges:
        graph[parent].append(child)
    return graph

def dfs(cur, nxt_nodes, sheep, wolf, graph, info):
    global answer
    if sheep == wolf:
        return
    nxt_nodes.extend(graph[cur])
    nxt_nodes.remove(cur)
    answer = max(sheep, answer)
    
    for nxt in nxt_nodes:
        if info[nxt] == 1:
            dfs(nxt, nxt_nodes.copy(), sheep, wolf+1, graph, info)
        else:
            dfs(nxt, nxt_nodes.copy(), sheep+1, wolf, graph, info)
    
def solution(info, edges):
    global answer
    answer = 0
    graph = make_graph(info, edges)
    dfs(0, [0], 1, 0, graph, info)
    return answer
