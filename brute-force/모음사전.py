answer = 0
alphabet = ['A', 'E', 'I', 'O', 'U']

def dfs(dic_word, num, word):
    global answer
    if dic_word == word:
        answer = num
        return num
    if len(dic_word) == 5:
        return num
    for a in alphabet:
        num = dfs(dic_word + a, num + 1, word)
    return num

def solution(word):
    dfs("", 0, word)
    return answer
