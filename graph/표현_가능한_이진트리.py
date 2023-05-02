import math
index = 0
def inorder(node, tree, N, str_b):
    global index
    left = node * 2
    right = left + 1
    if right <= N:
        inorder(right, tree, N, str_b)
    if index >= 0:
        tree[node] = int(str_b[index])
        index -= 1
    if left <= N:
        inorder(left, tree, N, str_b)

def make_tree(str_b):
    global index
    M = len(str_b)
    index = M-1
    height = math.ceil(math.log(M+1, 2)-1)
    N = int(math.pow(2, height+1)) - 1
    tree = [0] * (N+1)
    inorder(1, tree, N, str_b)
    return tree

def is_binary_tree(tree):
    N = len(tree)-1
    for i in range(1, (N-1)//2+1):
        left = i * 2
        right = left + 1
        if tree[i] == 0 and (tree[left] == 1 or tree[right] == 1):
            return False
    return True

def solve(num):
    str_b = str(format(num, 'b'))
    tree = make_tree(str_b)
    if is_binary_tree(tree):
        return 1
    return 0

def solution(numbers):
    answer = []
    for num in numbers:
        answer.append(solve(num))
    return answer
