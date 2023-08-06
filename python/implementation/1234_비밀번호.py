def solve(N, password):
    while N > 0:
        next_n = N
        for i in range(N-1):
            if password[i] == password[i+1]:
                password = password[:i] + password[i+2:]
                next_n -= 2
                break
        if next_n == N:
            break
        N = next_n
    return "".join(password)
    
T = 10
for test_case in range(1, T + 1):
    N, password = map(str, input().split())
    answer = solve(int(N), list(password))
    print(f"#{test_case} {answer}")
