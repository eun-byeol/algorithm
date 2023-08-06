T = 10
for test_case in range(1, T + 1):
    N = int(input())
    data = list(map(int, input().split()))
    boxes = []
    for i, h in enumerate(data):
        boxes.append([h, i])
    boxes.sort()
    max_diff = boxes[-1][0] - boxes[0][0]
    for _ in range(N):
        boxes[-1][0] -= 1
        boxes[0][0] += 1
        boxes.sort()
        max_diff = boxes[-1][0] - boxes[0][0]
    print(f"#{test_case} {max_diff}")
