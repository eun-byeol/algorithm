from itertools import permutations
def is_prime(number):
    if number == 0 or number == 1:
        return False
    for n in range(2, int(number ** 0.5) + 1):
        if number % n == 0:
            return False
    return True

def solution(numbers):
    answer = 0
    primes = set()
    for i in range(1, len(numbers)+1):
        for p in permutations(numbers, i):
            num = int(''.join(p))
            if num in primes:
                continue
            if is_prime(num):
                primes.add(num)
                answer += 1
    return answer
