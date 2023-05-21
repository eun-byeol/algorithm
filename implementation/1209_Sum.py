def solve(data):
    answer = 0
    for i in range(100):
        row = 0
        col = 0
        for j in range(100):
            row += data[i][j]
            col += data[j][i]
        answer = max(row, col, answer)
    up = 0
    down = 0
    for i in range(100):
        down += data[i][i]
        up += data[i][99-i]
    answer = max(up, down, answer)
    return answer

T = 10
for _ in range(1, T + 1):
    test_case = int(input())
    data = []
    for _ in range(100):
        data.append(list(map(int, input().split())))
    answer = solve(data)
    print(f"#{test_case} {answer}")
