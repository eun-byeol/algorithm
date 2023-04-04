def solution(progresses, speeds):
    answer = []
    for prg, spd in zip(progresses, speeds):
        day = -((prg - 100) // spd)
        if len(answer) == 0 or answer[-1][0] < day:
            answer.append([day, 1])
        else:
            answer[-1][1] += 1
    return [a[1] for a in answer]
