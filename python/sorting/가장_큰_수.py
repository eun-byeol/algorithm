
def solution(numbers):
    str_num = [str(num) for num in numbers]
    str_num.sort(key = lambda x : x*3, reverse = True)
    answer = str(int(''.join(str_num)))
    return answer
