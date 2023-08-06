def init_dict():
    values = list(range(1,27))
    keys = list("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
    return dict(zip(keys, values))

def solution(msg):
    answer = []
    words = init_dict()
    words_num = 26
    
    i, j = 0, 0
    while i < len(msg):
        idx = -1
        while idx != 0 and j < len(msg):
            j += 1
            idx = words.get(msg[i:j], 0)
        if j == len(msg) and idx != 0:
            answer.append(words.get(msg[i:j]))
        else:
            answer.append(words.get(msg[i:j-1]))
            words_num += 1
            words[msg[i:j]] = words_num
            j -= 1
        i = j
    return answer
