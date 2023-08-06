def solution(name):
    answer = 0
    name_length = len(name)
    min_dist = name_length-1

    for i, char in enumerate(name):
        answer += min(ord(char)-ord('A'), ord('Z')-ord(char)+1)

        next = i+1
        while next < name_length and name[next] == 'A':
            next += 1

        min_dist = min(min_dist, i*2 + name_length-next, (name_length-next)*2 + i)
    answer += min_dist
    return answer
