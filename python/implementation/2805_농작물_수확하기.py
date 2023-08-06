T = int(input())
for test_case in range(1, T + 1):
    N = int(input())
    total = 0
    left = right = N//2
    for i in range(N):
        data = list(input())
        for j in range(left, right+1):
            total += int(data[j])
        if i >= N//2:
            left += 1
            right -= 1
        else:
            left -= 1
            right += 1
    print(f"#{test_case} {total}")
