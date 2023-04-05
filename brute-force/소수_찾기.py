from itertools import permutations
def is_valid(number):
    if number == 0 or number == 1:
        return False
    for n in range(2, number):
        if number % n == 0:
            return False
    return True

def solution(numbers):
    answer = 0
    result = set()
    for i in range(1, len(numbers)+1):
        for permu in permutations(numbers, i):
            tmp = ""
            for num in permu:
                tmp += num
            number = int(tmp)
            if number in result:
                continue
            if is_valid(number):
                result.add(number)
                answer += 1
    return answer
