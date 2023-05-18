def is_valid(n):
    s = str(n)
    for i in range(len(s)-1):
        if s[i] > s[i+1]:
            return False
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
