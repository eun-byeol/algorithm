from collections import defaultdict
import math

def hour_to_minute(time):
    return int(time[:2]) * 60 + int(time[3:])

def make_data(records):
    usage_time = defaultdict(int)
    history = {}
    for record in records:
        time, num, state = record.split(' ')
        if state == "IN":
            history[num] = hour_to_minute(time)
        else:
            go_in = history.pop(num)
            go_out = hour_to_minute(time)
            usage_time[num] += go_out - go_in
    go_out = hour_to_minute("23:59")
    for num in history:
        go_in = history[num]
        usage_time[num] += go_out - go_in
    return usage_time

def solution(fees, records):
    answer = []
    data = make_data(records)
    for num, minutes in sorted(data.items()):
        if minutes <= fees[0]:
            answer.append(fees[1])
        else:
            answer.append(fees[1] + math.ceil((minutes - fees[0]) / fees[2]) * fees[3])
    return answer
