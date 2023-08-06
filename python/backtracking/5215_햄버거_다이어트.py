def dfs(total_cal, total_score, at):
    global answer
    for i in range(at, N):
        rate, score, cal = food[i]
        if total_cal + cal <= L:
            dfs(total_cal+cal, total_score+score, i+1)
    answer = max(total_score, answer)
    
T = int(input())
for test_case in range(1, T + 1):
    global answer
    answer = 0
    N, L = map(int, input().split())
    food = []
    for _ in range(N):
        s, c = map(int, input().split())
        food.append((s/c, s, c))
    food.sort(reverse=True)
    dfs(0, 0, 0)
    print(f"#{test_case} {answer}")
