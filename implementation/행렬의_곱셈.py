def solution(arr1, arr2):
    K = len(arr1[0])
    M = len(arr1)
    N = len(arr2[0])
    answer = [[0] * N for _ in range(M)]
    
    for i in range(M):
        for j in range(N):
            for l in range(K):
                answer[i][j] += arr1[i][l] * arr2[l][j]
    return answer
