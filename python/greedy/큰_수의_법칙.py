n, m, k = map(int, input().split())
data = list(map(int, input().split()))
data.sort()

first = data[-1]
second = data[-2]

first_cnt = (m//(k+1)) * k + m%(k+1)
second_cnt = m//(k+1)

result = first_cnt * first + second_cnt * second

print(result)

'''
5 8 3
2 4 5 4 6
'''
