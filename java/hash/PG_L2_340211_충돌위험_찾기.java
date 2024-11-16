import java.util.*;

class Node {
    int time, r, c;
    
    public Node(int time, int r, int c) {
        this.time = time;
        this.r = r;
        this.c = c;
    }
    
    public boolean equals(Object o) {
        Node ob = (Node) o;
        return time == ob.time && r == ob.r && c == ob.c;
    }
    
    public int hashCode() {
        return Objects.hash(time, r, c);
    }
}

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int robot = routes.length;
        Map<Node, Integer> history = new HashMap<>();
        
        for (int[] route : routes) {
            int time = 0;
            
            int sx = points[route[0]-1][0]-1;
            int sy = points[route[0]-1][1]-1;
        
            Node node = new Node(++time, sx, sy);
            int cnt = history.getOrDefault(node, 0);
            history.put(node, cnt+1);
            
            for (int k=1; k<route.length; k++) {
                int tx = points[route[k]-1][0]-1;
                int ty = points[route[k]-1][1]-1;
                
                if (sx > tx) {
                    for (int r=sx-1; r>=tx; r--) {
                        node = new Node(++time, r, sy);
                        cnt = history.getOrDefault(node, 0);
                        history.put(node, cnt+1);
                    }
                }
                else if (sx < tx) {
                    for (int r=sx+1; r<=tx; r++) {
                        node = new Node(++time, r, sy);
                        cnt = history.getOrDefault(node, 0);
                        history.put(node, cnt+1);
                    }
                }

                if (sy > ty) {
                    for (int c=sy-1; c>=ty; c--) {
                        node = new Node(++time, tx, c);
                        cnt = history.getOrDefault(node, 0);
                        history.put(node, cnt+1);
                    }
                }
                else if (sy < ty) {
                    for (int c=sy+1; c<=ty; c++) {
                        node = new Node(++time, tx, c);
                        cnt = history.getOrDefault(node, 0);
                        history.put(node, cnt+1);
                    }
                }
                sx = tx;
                sy = ty;
            }
        }
        
        int answer = 0;
        for (Node key : history.keySet()) {
            if (history.get(key) > 1) answer++;
        }
        
        return answer;
    }
}

// history[time][r][c] 배열로 풀면 런타임 에러 -> Map<Node>로 풀어 해결 (hashCode, equals 재정의 필요..)
