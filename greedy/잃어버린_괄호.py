sp_input = input().split('-')
total = 0

for num in sp_input[0].split('+'):
    total += int(num)

for arr in sp_input[1:]:
    for num in arr.split('+'):
        total -= int(num)
print(total)
