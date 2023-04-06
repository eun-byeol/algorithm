def dfs(idx, total, numbers, target):
    if idx == len(numbers):
        if total == target:
            return 1
        return 0
    return dfs(idx+1, total+numbers[idx], numbers, target) + dfs(idx+1, total-numbers[idx], numbers, target)

def solution(numbers, target):
    answer = dfs(0, 0, numbers, target)
    return answer
