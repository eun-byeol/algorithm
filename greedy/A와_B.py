S = input()
T = input()

i = len(T)-1
while len(T) > len(S):
    if T[i] == 'A':
        T = T[:i]
    elif T[i] == 'B':
        T = T[i-1::-1]
    i -= 1

if T == S:
    print(1)
else:
    print(0)
