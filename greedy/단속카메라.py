def solution(routes):
    answer = 0
    routes.sort(key = lambda x : x[1])
    camera = -30001

    for into, out in routes:
        if into > camera:
            answer += 1
            camera = out
    return answer
