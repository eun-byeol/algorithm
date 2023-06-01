def init(n):
    table = []
    for i in range(n):
        table.append([i-1, i+1])
    table[0][0] = None
    table[-1][1] = None
    return table

def delete_node(table, k, st):
    st.append(k)
    pre, nxt = table[k]
    if pre != None:
        table[pre][1] = nxt
    if nxt != None:
        table[nxt][0] = pre
    if nxt == None:
        k = pre
    else:
        k = nxt
    return k

def restore_node(table, st):
    cur = st.pop()
    pre, nxt = table[cur]
    if pre != None:
        table[pre][1] = cur
    if nxt != None:
        table[nxt][0] = cur
    return cur
    
def move_up(table, x, k):
    for _ in range(x):
        k = table[k][0]
    return k

def move_down(table, x, k):
    for _ in range(x):
        k = table[k][1]
    return k
    
def solution(n, k, cmd):
    table = init(n)
    answer = ["O"] * n
    st = []
    
    for info in cmd:
        if info == "C":
            answer[k] = "X"
            k = delete_node(table, k, st)
            continue
        if info == "Z":
            idx = restore_node(table, st)
            answer[idx] = "O"
            continue
        c, x = info.split(" ")
        if c == "U":
            k = move_up(table, int(x), k)
            continue
        k = move_down(table, int(x), k)
    return "".join(answer)
