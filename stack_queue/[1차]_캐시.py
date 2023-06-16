from collections import deque
def solution(cacheSize, cities):
    answer = 0
    checked = {}
    q = deque([])
    for time, city in enumerate(cities):
        city = city.lower()
        if city in checked:
            info = checked[city]
            idx = q.index(info)
            q[idx] = (time, city)
            checked[city] = (time, city)
            answer += 1
        else:
            q.append((time, city))
            checked[city] = (time, city)
            if len(q) > cacheSize:
                rm_info = q.popleft()
                checked.pop(rm_info[1])
            answer += 5
        q = deque(sorted(list(q)))
    return answer
