import math
def solution(progresses, speeds):
    answer = []
    days = []
    for prg, spd in zip(progresses, speeds):
        days.append(math.ceil((100 - prg) / spd))
        
    release = 0
    cnt = 0
    for i in range(len(days)):
        if days[i] > days[release]:
            answer.append(cnt)
            release = i
            cnt = 0
        cnt += 1
    answer.append(cnt)
    return answer
