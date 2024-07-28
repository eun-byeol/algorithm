import java.util.*;

class Solution {
    Map<String, List<Integer>> dic = new HashMap<>();
    List<String>[] options = new List[]{
        List.of("cpp", "java", "python"),
        List.of("backend", "frontend"),
        List.of("junior", "senior"),
        List.of("chicken", "pizza")
    };
    int cnt;
    
    public int[] solution(String[] info, String[] query) { 
        
        for (String row : info) {
            String[] r = row.split(" and | ");
            String key = String.join("", Arrays.copyOfRange(r, 0, 4));
            List<Integer> scores = dic.getOrDefault(key, new ArrayList<>());
            scores.add(Integer.parseInt(r[4]));
            dic.put(key, scores);
        }
        
        for (List<Integer> scores : dic.values()) {
            Collections.sort(scores, Collections.reverseOrder());
        }
        
        int N = query.length;
        int[] answer = new int[N];
        for (int i=0; i<N; i++) {
            String row = query[i];
            String[] r = row.split(" and | ");
            dfs(0, 0, "", r);
            answer[i] = cnt;
            cnt = 0;
        }
        
        return answer;
    }
    
    void dfs(int count, int index, String key, String[] r) {
        if (index == 4) {
            if (dic.containsKey(key)) {
                List<Integer> scores = dic.get(key);
                cnt += lowerBound(scores, Integer.parseInt(r[4]));
            }
            return;
        }
        
        if (r[index].equals("-")) {
            for (String op : options[index]) {
                dfs(count, index+1, key+op, r);
            }
        }
        else {
            dfs(count, index+1, key+r[index], r);
        }
    }
    
    int lowerBound(List<Integer> scores, int target) {
        int left = 0;
        int right = scores.size();
        
        while(left < right) {
            int mid = (left + right) / 2;
            
            if (scores.get(mid) < target) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return right;
    }
}
/*
핵심 - 도 전부 추가해준다.

1. 저장
    - 옵션은 key-value로
    - 점수는 정렬하여(마지막에 한번에)
2. 조회
    - 이분탐색
*/
