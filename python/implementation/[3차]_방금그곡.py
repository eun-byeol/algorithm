def translate(melody):
    result = []
    for i, c in enumerate(melody):
        if c == "#":
            result[-1] = result[-1].lower()
            continue
        result.append(c)
    return ''.join(result)

def change_to_minutes(t):
    return int(t[:2]) * 60 + int(t[3:])

def calculate_time(start, end):
    return change_to_minutes(end) - change_to_minutes(start)

def make_song(time, melody):
    N = len(melody)
    return melody * (time//N) + melody[:(time%N)]
    
def solution(m, musicinfos):
    answer = ["(None)", 0]
    m = translate(m)
    for info in musicinfos:
        start, end, name, melody = info.split(',')
        time = calculate_time(start, end)
        song = make_song(time, translate(melody))
        if m in song:
            if answer[1] < time:
                answer = name, time
    return answer[0]
