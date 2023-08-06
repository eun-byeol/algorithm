class Solution {
    int[] findIndex(int[] sequence, int k) {
        int[] result = {0, sequence.length};
        int start = 0;
        int total = 0;

        for (int end=0; end < sequence.length; end++) {
            total += sequence[end];
            if (total == k) {
                if (result[1] - result[0] > end - start) {
                    result[0] = start;
                    result[1] = end;
                }
            }
            else if (total > k) {
                while (start < end) {
                    total -= sequence[start];
                    start++;
                    if (total == k) {
                        if (result[1] - result[0] > end - start) {
                            result[0] = start;
                            result[1] = end;
                            break;
                        }
                    }
                    else if (total < k) {
                        break;
                    }
                }
            }
        }
        return result;
    }

    public int[] solution(int[] sequence, int k) {
        int[] answer = findIndex(sequence, k);
        return answer;
    }
}
