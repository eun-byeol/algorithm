def solution(n, words):
    answer = [0, 0]
    history = {}
    pre = words[0][0]
    
    for i, word in enumerate(words):
        if word in history or pre != word[0]:
            answer = [i % n + 1, i // n + 1]
            break
        pre = word[-1]
        history[word] = 1
    return answer
