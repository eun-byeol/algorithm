T = 10

for test_case in range(1, T + 1):
    N = int(input())
    data = list(map(int, input().split()))
    cnt = 0

    for i in range(2, len(data)-2):
        pre, ppre, cur, nxt, nnxt = data[i-2:i+3]
        pre_diff = cur - max(pre, ppre)
        nxt_diff = cur - max(nxt, nnxt)
        if pre_diff <= 0 or nxt_diff <= 0:
            continue
        cnt += min(pre_diff, nxt_diff)
    print("#{0} {1}".format(test_case, cnt))
