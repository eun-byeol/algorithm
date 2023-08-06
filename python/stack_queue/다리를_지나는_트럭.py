from collections import deque
def solution(bridge_length, weight, truck_weights):
    trucks = deque(truck_weights)
    bridge = deque([0] * bridge_length)

    time, total = 0, 0
    while True:
        time += 1
        total -= bridge.popleft()
        if trucks and trucks[0] + total <= weight:
            total += trucks[0]
            bridge.append(trucks.popleft())
        else:
            bridge.append(0)
        if total == 0:
            break
    return time
