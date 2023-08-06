num = input()

result = int(num[0])

for i in range(1, len(num)):
  if result <= 1 or int(num[i]) <= 1:
    result += int(num[i])
    continue
  result *= int(num[i])

print(result)

'''
02984
'''
