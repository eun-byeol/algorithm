def find_max_value(nums, N):
    max_v = "".join(map(str, nums))
    sorted_nums = sorted(list(zip(nums, range(N))), key = lambda x : (-x[0], -x[1]))
    for i in range(N):
        max_num, max_i = sorted_nums[i]
        for j in range(N):
            if max_num > nums[j] and max_i > j:
                nums[j], nums[max_i] = nums[max_i], nums[j]
                return max("".join(map(str, nums)), max_v)
    return max_v

def find_min_value(nums, N):
    min_v = "".join(map(str, nums))
    sorted_nums = sorted(list(zip(nums, range(N))), key = lambda x : (x[0], -x[1]))
    for i in range(N):
        min_num, min_i = sorted_nums[i]
        at = 1 if min_num == 0 else 0
        for j in range(at, N):
            if min_num < nums[j] and min_i > j:
                tmp = [v for v in nums]
                tmp[j], tmp[min_i] = tmp[min_i], tmp[j]
                min_v = min("".join(map(str, tmp)), min_v)
                if min_num != 0:
                    return min_v
                break
    return min_v

T = int(input())
for test_case in range(1, T + 1):
    nums = list(input())
    N = len(nums)
    max_v = find_max_value([int(v) for v in nums], N)
    min_v = find_min_value([int(v) for v in nums], N)
    print(f"#{test_case} {min_v} {max_v}")
