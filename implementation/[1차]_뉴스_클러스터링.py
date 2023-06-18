def split(s):
    result = []
    for i in range(len(s)-1):
        if 'a' <= s[i] <= 'z' and 'a' <= s[i+1] <= 'z':
            result.append(s[i:i+2])
    return result

def calculate(d1, d2):
    if len(d1) == 0 and len(d2) == 0:
        return 1
    
    union, inter = [], []
    for v in d1:
        union.append(v)
        if v in d2:
            inter.append(v)
            d2.remove(v)
    union.extend(d2)
    return len(inter) / len(union)
            
def solution(str1, str2):
    data1 = split(str1.lower())
    data2 = split(str2.lower())
    return int(calculate(data1, data2) * 65536)
