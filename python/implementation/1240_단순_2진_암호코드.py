code = {
    "0001101": 0,
    "0011001": 1,
    "0010011": 2,
    "0111101": 3,
    "0100011": 4,
    "0110001": 5,
    "0101111": 6,
    "0111011": 7,
    "0110111": 8,
    "0001011": 9
}

def is_valid(num):
    total = 0
    for i in range(0, 8, 2):
        total += num[i]
    total *= 3
    for i in range(1, 8, 2):
        total += num[i]
    return total % 10 == 0
    
def solve(data, M):
    num = []
    start, end = 0, 0
    for i in range(M-1, -1, -1):
        if data[i] == '1':
            start = i-55
            end = i
            break
    for i in range(start, end, 7):
        bit = data[i:i+7]
        num.append(code[bit])
    if is_valid(num):
        return sum(num)
    return 0

T = int(input())
for test_case in range(1, T + 1):
    N, M = map(int, input().split())
    answer = -1
    for _ in range(N):
        data = input()
        if int(data) != 0 and answer == -1:
            answer = solve(data, M)
    print(f"#{test_case} {answer}")
