def solution(s):
    answer = ''
    for i, word in enumerate(s.split(' ')):
        if word == '':
            answer += ' '
            continue
        if i != 0:
            answer += ' '
        if word[0].isdigit():
            answer += word[0] + word[1:].lower()
        else:
            answer += word[0].upper() + word[1:].lower()
    return answer
