def solution(number, k):
    num_list = list(number)
    cnt = k
    for cur in range(len(num_list)-1):
        next = cur + 1
        if int(num_list[cur]) < int(num_list[next]):
            num_list[cur] = ' '
            cnt -= 1
            for pre in range(cur-1, -1, -1):
                if num_list[pre] == ' ':
                    continue
                if cnt == 0 or int(num_list[pre]) >= int(num_list[next]):
                    break
                num_list[pre] = ' '
                cnt -= 1
        if cnt == 0:
            break
    answer = "".join(num for num in num_list if num != ' ')
    return answer[0:len(answer)-cnt]
