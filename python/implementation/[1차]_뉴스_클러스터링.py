from collections import Counter

def split(s):
    result = []
    for i in range(len(s)-1):
        if s[i:i+2].isalpha():
            result.append(s[i:i+2])
    return result

def calculate(d1, d2):
    counter1 = Counter(d1)
    counter2 = Counter(d2)
    
    inter = list((counter1 & counter2).elements())
    union = list((counter1 | counter2).elements())
    
    if len(union) == 0:
        return 1
    return len(inter) / len(union)
            
def solution(str1, str2):
    data1 = split(str1.lower())
    data2 = split(str2.lower())
    return int(calculate(data1, data2) * 65536)
