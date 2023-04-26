class Solution {
    int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a%b);
    }
    
    int getGcd(int[] array) {
        int num = gcd(array[0], array[0]);
        for (int i=1; i<array.length; i++) {
            num = gcd(num, array[i]);
        }
        return num;
    }
    
    boolean isDivided(int[] array, int num) {
        for (int a : array) {
            if (a % num == 0) {
                return true;
            }
        }
        return false;
    }
    
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        int gcdA = getGcd(arrayA);
        int gcdB = getGcd(arrayB);
        
        if (gcdA > gcdB) {
            if (!isDivided(arrayB, gcdA)) {
                return gcdA;
            }
            if (!isDivided(arrayA, gcdB)) {
                return gcdB;
            }
        }
        else if (gcdA < gcdB) {
            if (!isDivided(arrayA, gcdB)) {
                return gcdB;
            }
            if (!isDivided(arrayB, gcdA)) {
                return gcdA;
            }
        }
        return answer;
    }
}
