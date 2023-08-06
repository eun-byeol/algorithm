T = 10
for _ in range(1, T + 1):
    test_case = int(input())
    word = input()
    sentence = input()
    answer = sentence.count(word)
    print(f"#{test_case} {answer}")
