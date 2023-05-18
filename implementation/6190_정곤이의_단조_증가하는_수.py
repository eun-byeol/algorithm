def is_valid(n):
    pre = n % 10
    while n > 0:
        cur = n % 10
        if cur > pre:
            return False
        pre = cur
        n //= 10
    return True

def solve(N, nums):
    data = set()
    for i, n1 in enumerate(nums):
        for n2 in nums[i+1:]:
            data.add(n1*n2)
    sorted_data = sorted(list(data), reverse=True)
    for d in sorted_data:
        if is_valid(d):
            return d
    return -1

T = int(input())
for test_case in range(1, T + 1):
    N = int(input())
    nums = list(map(int, input().split()))
    answer = solve(N, nums)
    print(f"#{test_case} {answer}")
