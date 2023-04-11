from itertools import permutations
def solution(k, dungeons):
    answer = 0
    for permu in permutations(dungeons, len(dungeons)):
        energy = k
        cnt = 0
        for before, after in permu:
            if energy >= before:
                cnt += 1
                energy -= after
            else:
                break
        answer = max(answer, cnt)
    return answer
