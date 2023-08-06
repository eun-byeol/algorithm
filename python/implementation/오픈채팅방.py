def solution(record):
    answer = []
    user_id = {}
    rst = []
    for rc in record:
        info = rc.split(' ')
        if info[0] == "Leave":
            cmd, uid = info
        else:
            cmd, uid, nick = info
            user_id[uid] = nick
        if cmd != "Change":
            rst.append((uid, cmd))
    
    for uid, cmd in rst:
        s = user_id[uid] + "님이 "
        if cmd == "Enter":
            s += "들어왔습니다."
        else:
            s += "나갔습니다."
        answer.append(s)
    return answer
