def solution(s):
    turn, zero = 0, 0
    while s != "1":
        n = s.count('1')
        zero += len(s) - n
        s = format(n, 'b')
        turn += 1
    return [turn, zero]
