import sys
import re
inputs = sys.stdin.readline()
nums = list(map(int, re.split(r'[-+]', inputs)))
tmp = re.split('\d', inputs)
ops = []
for op in tmp:
    if op == "+" or op == "-":
        ops.append(op)

total = nums[0]
st = []
for op, n in zip(ops, nums[1:]):
    if op == "-":
        while st:
            total -= st.pop()
        st.append(n)
        continue
    if st:
        st.append(n)
    else:
        total += n
while st:
    total -= st.pop()
print(total)
