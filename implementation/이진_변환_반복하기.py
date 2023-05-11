def solution(s):
    turn, zero = 0, 0
    while s != "1":
        tmp = ""
        for v in s:
            if v == "1":
                tmp += v
            else:
                zero += 1
        s = format(len(tmp), 'b')
        turn += 1
    return [turn, zero]
