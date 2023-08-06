def solution(phone_book):
    phone_book.sort()
    length = len(phone_book)
    for i in range(length-1):
        if phone_book[i+1].startswith(phone_book[i]):
            return False
    return True


#hash map 이용 풀이

def solution(phone_book):
    dict = {}
    for numbers in phone_book:
        dict[numbers] = 1
    for numbers in phone_book:
        tmp = ""
        for num in numbers:
            tmp += num
            if tmp in dict and tmp != numbers:
                return False
    return True
