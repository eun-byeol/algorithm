def solve(n):
    bin_n = format(n, 'b')
    N = len(bin_n)
    for i in range(N-1, -1, -1):
        if bin_n[i] == '0':
            if i == N-1:
                return int('0b' + bin_n[:i] + '1' + bin_n[i+1:], 2)
            return int('0b' + bin_n[:i] + '10' + bin_n[i+2:], 2)
    return int('0b10' + bin_n[1:], 2)
    
def solution(numbers):
    answer = []
    for num in numbers:
        answer.append(solve(num))
    return answer
