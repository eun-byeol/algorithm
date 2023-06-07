from functools import cmp_to_key
def split_name(file):
    p, q = len(file), len(file)
    for i, c in enumerate(file):
        if '0' <= c <= '9':
            p = i
            break
    for j in range(p, len(file)):
        if '0' <= file[j] <= '9':
            continue
        q = j
        break
    head = file[:p].lower()
    number = int(file[p:q])
    return head, number

def comparator(a, b):
    a_head, a_number = split_name(a)
    b_head, b_number = split_name(b)
    
    if a_head == b_head:
        return a_number - b_number
    elif a_head > b_head:
        return 1
    return -1
    
def solution(files):
    for file in files:
        files.sort(key=cmp_to_key(comparator))
    return files
