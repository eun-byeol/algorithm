def is_palindrome(L, data):
    for row in data:
        for s in range(100-L+1):
            left = s
            right = s+L-1
            while left < right:
                if row[left] != row[right]:
                    break
                left += 1
                right -= 1
            if left >= right:
                return True
    return False

def solve(data):
    for l in range(100, 1, -1):
        if is_palindrome(l, data) or is_palindrome(l, list(zip(*data))):
            return l
    return 1
        

T = 10
for _ in range(1, T + 1):
    test_case = int(input())
    data = [list(input()) for _ in range(100)]
    answer = solve(data)
    print(f"#{test_case} {answer}")
