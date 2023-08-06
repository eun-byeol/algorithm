import sys

def binary_search(target):
    left = 0
    right = N-1
    while left <= right:
        mid = (left + right) // 2
        s = set_data[mid][:len(target)]
        if s == target:
            return True
        elif set_data[mid] < target:
            left = mid + 1
        else:
            right = mid - 1
    return False

N, M = map(int, sys.stdin.readline().rstrip().split())
set_data = [sys.stdin.readline().rstrip() for _ in range(N)]
test_words = [sys.stdin.readline().rstrip() for _ in range(M)]

set_data.sort()

cnt = 0
for word in test_words:
    if binary_search(word):
        cnt += 1
print(cnt)
