import java.util.*;

class Solution {
    int N;
    Map<String, Integer>[] maps = new HashMap[11];
    
    public String[] solution(String[] orders, int[] course) {
        N = course.length;
        List<String> answer = new ArrayList<>();
        
        for (int c : course) {
            maps[c] = new HashMap<String, Integer>();
        }
        
        for (String order : orders) { // orders 원소 정렬
            char[] od = order.toCharArray();
            Arrays.sort(od);
            pick(od, course);
        }
        
        for (int c : course) { // 가장 인기 많은 조합 찾기
            max(maps[c], c, answer);
        }
        
        Collections.sort(answer); // 오름차순 정렬
        
        return answer.toArray(new String[answer.size()]);
    }
    
    void max(Map<String, Integer> map, int count, List<String> answer) {
        if (map.size() == 0) {
            return;
        }
        Collection<Integer> values = map.values();
        int maxV = Collections.max(values);
        if (maxV < 2) { // 최소 2명 이상 주문된 단품 메뉴 조합
            return;
        }
        for (String key : map.keySet()) {
            if (map.get(key) == maxV) {
                answer.add(key);
            }
        }
    }
    
    void pick(char[] order, int[] course) {
        for (int c : course) {
            combi(0, new char[c], 0, c, order.length, order);
        }
    }
    
    void combi(int index, char[] result, int at, int K, int M, char[] order) { // M개 원소 중 K개 뽑기
        if (index == K) {
            String menu = String.valueOf(result);
            int cnt = maps[K].getOrDefault(menu, 0);
            maps[K].put(menu, cnt+1);
            return;
        }
        if (at == M) {
            return;
        }
        for (int i=at; i<M; i++) {
            result[index] = order[i];
            combi(index+1, result, i+1, K, M, order);
        }
    }
}
