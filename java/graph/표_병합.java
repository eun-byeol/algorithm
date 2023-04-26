import java.util.*;
class Solution {
    int N = 51 * 51;
    int[] parents = new int[N];
    Map<Integer, String> map = new HashMap<>();
    String[] printStr = new String[1001];
    int printIdx = 0;
    
    void init() {
        for (int i=0; i<N; i++) {
            parents[i] = i;
        }
    }
    
    int encode(int r, int c) {
        int idx = 0;
        idx = 50 * (r-1);
        idx += (c-1);
        return idx;
    }
    
    int find(int idx) {
        if (parents[idx] == idx) {
            return idx;
        }
        return find(parents[idx]);
    }
    
    void update1(int r, int c, String value) {
        int idx = encode(r, c);
        int p = find(idx);
        map.put(p, value);
    }
    
    void update2(String v1, String v2) {
        for (Integer key : map.keySet()) {
            if (map.get(key).equals(v1)) {
                map.replace(key, v2);
            }
        }
    }
    
    void merge(int r1, int c1, int r2, int c2) {
        int idx1 = encode(r1, c1);
        int idx2 = encode(r2, c2);
        int p1 = find(idx1);
        int p2 = find(idx2);
        if (p1 == p2) {
            return;
        }
        if (map.containsKey(p1) && map.containsKey(p2)) {
            for (int i=0; i<N; i++) {
                if (parents[i] == p2) {
                    parents[i] = p1;
                }
            }
            map.remove(p2);
        }
        else if (!map.containsKey(p1)) {
            for (int i=0; i<N; i++) {
                if (parents[i] == p1) {
                    parents[i] = p2;
                }
            }
        }
        else { //(r2, c2)의 value가 없는 경우 or 둘 다 value가 없는 경우
            for (int i=0; i<N; i++) {
                if (parents[i] == p2) {
                    parents[i] = p1;
                }
            }
        }
    }
    
    void unmerge(int r, int c) {
        int idx = encode(r, c);
        int p = find(idx);
        for (int i=0; i<N; i++) {
            if (parents[i] == p) {
                parents[i] = i;
            }
        }
        if (map.containsKey(p)) {
            map.put(idx, map.get(p));
        }
        if (idx != p) {
            map.remove(p);
        }
    }
    
    void print(int r, int c) {
        int idx = encode(r, c);
        int p = find(idx);
        printStr[printIdx++] = map.getOrDefault(p, "EMPTY");
    }
    
    public String[] solution(String[] commands) {
        init();
        for (String command : commands) {
            String[] cmd = command.split(" ");
            if (cmd[0].equals("UPDATE")) {
                if (cmd.length == 4) {
                    int r = Integer.parseInt(cmd[1]);
                    int c = Integer.parseInt(cmd[2]);
                    String v = cmd[3];
                    update1(r, c, v);
                }
                else {
                    String v1 = cmd[1];
                    String v2 = cmd[2];
                    update2(v1, v2);
                }
            }
            else if (cmd[0].equals("MERGE")) {
                int r1 = Integer.parseInt(cmd[1]);
                int c1 = Integer.parseInt(cmd[2]);
                int r2 = Integer.parseInt(cmd[3]);
                int c2 = Integer.parseInt(cmd[4]);
                merge(r1, c1, r2, c2);
            }
            else if (cmd[0].equals("UNMERGE")) {
                int r = Integer.parseInt(cmd[1]);
                int c = Integer.parseInt(cmd[2]);
                unmerge(r, c);
            }
            else { //PRINT
                int r = Integer.parseInt(cmd[1]);
                int c = Integer.parseInt(cmd[2]);
                print(r, c);
            }
        }
        return Arrays.copyOfRange(printStr, 0, printIdx);
    }
}
