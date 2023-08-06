from collections import defaultdict
import re
data = defaultdict(list)

def dfs(cnt, at, N, s, score):
    global data
    if cnt == N:
        data["".join(s)].append(score)
        return
    for i in range(at, 4):
        tmp = s[i]
        s[i] = '-'
        dfs(cnt+1, i+1, N, s, score)
        s[i] = tmp

def make_data(info):
    global data
    for line in info:
        s = list(line.split())
        data["".join(s[:4])].append(int(s[4]))
        for n in range(1, 5):
            dfs(0, 0, n, s[:4], int(s[4]))
    for k in data.keys():
        data[k].sort()
        
def lower_bound(nums, target):
    left, right = 0, len(nums)
    while left < right:
        mid = (left + right) // 2
        if nums[mid] < target:
            left = mid+1
        else:
            right = mid
    return right

def simul(q):
    s = re.split(' and | ', q)
    key_s = "".join(s[:4])
    target_score = int(s[4])
    if len(data[key_s]) == 0:
        return 0
    lb = lower_bound(data[key_s], target_score)
    return len(data[key_s]) - lb
        
def solution(info, query):
    answer = []
    make_data(info)
    for q in query:
        answer.append(simul(q))
    return answer
