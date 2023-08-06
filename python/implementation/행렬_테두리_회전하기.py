data = []
def rotate(x1, y1, x2, y2):
    N = x2-x1+1
    M = y2-y1+1
    pre = data[x1+1][y1]
    min_num = pre
    
    for i in range(M): # 위
        tmp = data[x1][y1+i]
        data[x1][y1+i] = pre
        pre = tmp
        min_num = min(pre, min_num)
        
    for i in range(1, N): # 오른
        tmp = data[x1+i][y2]
        data[x1+i][y2] = pre
        pre = tmp
        min_num = min(pre, min_num)
        
    for i in range(1, M): # 아래
        tmp = data[x2][y2-i]
        data[x2][y2-i] = pre
        pre = tmp
        min_num = min(pre, min_num)
        
    for i in range(1, N): # 왼
        tmp = data[x2-i][y1]
        data[x2-i][y1] = pre
        pre = tmp
        min_num = min(pre, min_num)
    return min_num

def solution(rows, columns, queries):
    global data
    answer = []
    data = [[j for j in range((i-1)*columns+1, i*columns+1)] for i in range(1, rows+1)]
    for x1, y1, x2, y2 in queries:
        answer.append(rotate(x1-1, y1-1, x2-1, y2-1))
    return answer
