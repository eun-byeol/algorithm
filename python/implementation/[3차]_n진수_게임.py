num_str = "0123456789ABCDEF"

def transform(num, n):
    if num <= 1:
        return num_str[num]
    if num // n == 0:
        return num_str[num % n]
    return transform(num // n, n) + num_str[num % n]

def solution(n, t, m, p):
    answer = ''
    nums = ''
    next_num = 0
    for i in range(t):
        idx = p-1 + (i * m)
        while len(nums)-1 < idx:
            nums += transform(next_num, n)
            next_num += 1
        answer += nums[idx]
    return answer
