import math

def convert(n, k):
    result = []
    while n >= k:
        result.append(n % k)
        n //= k
    if n > 0:
        result.append(n)
    return ''.join(map(str, result[::-1]))

def is_prime(num):
    if num == 1:
        return False
    for i in range(2, int(math.sqrt(num))+1):
        if num % i == 0:
            return False
    return True
        
def solution(n, k):
    answer = 0
    num_str = convert(n, k)
    for num in num_str.split('0'):
        if len(num) == 0:
            continue
        if is_prime(int(num)):
            answer += 1
    return answer
