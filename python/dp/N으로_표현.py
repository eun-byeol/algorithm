def solution(N, number):
    answer = -1
    result = []
    
    for cnt in range(1, 9):
        tmp = set()
        tmp.add(int(str(N) * cnt))
        for i in range(cnt-1):
            for op1 in result[i]:
                for op2 in result[-i-1]:
                    tmp.add(op1 + op2)
                    tmp.add(op1 - op2)
                    tmp.add(op1 * op2)
                    if op2 != 0:
                        tmp.add(op1 // op2)
        if number in tmp:
            return cnt
        result.append(tmp)
    return answer
