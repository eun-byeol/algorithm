import java.util.*;
class Solution {
    int[] simul(int idx, int[] topping) {
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();
        for (int i=0; i<=idx; i++) {
            s1.add(topping[i]);
        }
        for (int i=idx+1; i<topping.length; i++) {
            s2.add(topping[i]);
        }
        return new int[]{s1.size(), s2.size()};
    }
    
    int upperSearch(int l, int r, int[] topping) {
        while (l < r) {
            int mid = (l + r) / 2;
            int[] num = simul(mid, topping);
            if (num[0] > num[1]) {
                r = mid;
            }
            else {
                l = mid+1;
            }
        }
        return l;
    }
    
    int lowerSearch(int l, int r, int[] topping) {
        while (l < r) {
            int mid = (l + r) / 2;
            int[] num = simul(mid, topping);
            if (num[0] >= num[1]) {
                r = mid;
            }
            else {
                l = mid+1;
            }
        }
        return l;
    }
    
    public int solution(int[] topping) {
        int N = topping.length;
        int upper = upperSearch(0, N-1, topping);
        int lower = lowerSearch(0, upper, topping);
        return upper - lower;
    }
}
