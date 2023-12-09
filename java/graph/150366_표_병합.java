import java.util.*;

class Solution {
    int N = 5051;
    int[] parent = new int[N];
    Map<Integer, String> values = new HashMap<>();
    
    public String[] solution(String[] commands) {
        initParent();
        List<String> answer = new LinkedList<>();
        
        for (String command : commands) {
            StringTokenizer st = new StringTokenizer(command);
            String cmd = st.nextToken();
            
            if (cmd.equals("UPDATE")) {
                if (st.countTokens() > 2) {
                    int r = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());
                    String value = st.nextToken();
                    updateOne(r, c, value);
                }
                else {
                    String value1 = st.nextToken();
                    String value2 = st.nextToken();
                    updateAll(value1, value2);
                }
            }
            else if (cmd.equals("MERGE")) {
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());
                merge(r1, c1, r2, c2);
            }
            else if (cmd.equals("UNMERGE")) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                unmerge(r, c);
            }
            else if (cmd.equals("PRINT")) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());   
                answer.add(print(r, c));
            }
        }
        return answer.toArray(new String[answer.size()]);
    }
    
    String print(int r, int c) {
        int code = encode(r, c);
        int p = parent[code];
        return values.getOrDefault(p, "EMPTY");
    }
    
    void unmerge(int r, int c) {
        int code = encode(r, c);
        int p = parent[code];
        String value = values.getOrDefault(p, null);
        
        for (int i=0; i<N; i++) {
            if (parent[i] == p) { // 같은 최상위 부모를 가진 모든 자식 노드를 초기화해줌
                parent[i] = i;
                if (values.containsKey(i)) {
                    values.remove(i);
                }
            }
        }
        if (value != null) {
            values.put(code, value); // r,c가 값을 가짐
        }
    }
    
    void merge(int r1, int c1, int r2, int c2) {
        int code1 = encode(r1, c1);
        int code2 = encode(r2, c2);
        int p1 = parent[code1];
        int p2 = parent[code2];
        
        // p1의 value가 없고, p2의 value가 있는 경우만 p2가 부모가 됨
        if (!values.containsKey(p1) && values.containsKey(p2)) {
            for (int i=0; i<N; i++) {
                if (parent[i] == p1) {
                    parent[i] = p2; // p1 -> p2
                }
            }
            return;
        }
        // 그 외 경우는 모두 p1이 부모가 됨
        for (int i=0; i<N; i++) {
            if (parent[i] == p2) {
                parent[i] = p1; // p2 -> p1
            }
        }
    }
    
    void updateAll(String v1, String v2) {
        for (int key : values.keySet()) {
            if (values.get(key).equals(v1)) {
                values.replace(key, v2);
            }
        }
    }
    
    void updateOne(int r, int c, String v) {
        int code = encode(r, c);
        int p = parent[code];
        values.put(p, v);
    }
    
    int encode(int r, int c) {
        return r * 100 + c;
    }
    
    void initParent() {
        for (int i=0; i<N; i++) {
            parent[i] = i; // 자기 자신을 최상위 부모로 초기화
        }
    }
}
