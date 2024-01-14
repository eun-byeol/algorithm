import java.util.*;

class Pair {
    int x;
    int y;
    
    Pair (int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public String toString () {
        return "(" + x + "," + y + ")";
    }
}

class Solution {
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0 , -1};
    int N = 4;
    int M = 7;
    int K;
    List<Pair>[] numIdx;
    List<Integer> nums;
    int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] board, int r, int c) {
        Set<Integer> numSet = new HashSet<>();
        numIdx = new List[M];
        
        for (int i=0; i<M; i++) {
            numIdx[i] = new ArrayList<>();
        }
        
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (board[i][j] > 0) {
                    int num = board[i][j];
                    numSet.add(num);
                    numIdx[num].add(new Pair(i, j));
                }
            }
        }
        nums = List.copyOf(numSet);
        K = nums.size();
        
        dfs(0, new int[M], new int[K], board, new Pair(r, c));

        return answer;
    }
    
    void dfs(int depth, int[] visited, int[] orders, int[][] board, Pair start) { // 순열
        if (depth == K) {
            answer = Math.min(simul(orders, board, start), answer);
            return;
        }
        
        for (int num : nums) {
            if (visited[num] == 1) continue;
            visited[num] = 1;
            orders[depth] = num;
            dfs(depth+1, visited, orders, board, start);
            visited[num] = 0;
            orders[depth] = 0;
        }
    }
    
    int simul(int[] orders, int[][] origin, Pair s) {
        int[][] board = new int[N][N];
        for (int i=0; i<N; i++) {
            board[i] = origin[i].clone();
        }
        
        int total = 0;
        for (int order : orders) {
            if (total > answer) {
                return Integer.MAX_VALUE;
            }
            Pair t1 = numIdx[order].get(0);
            Pair t2 = numIdx[order].get(1);
            int dist1 = bfs(board, s, t1);
            int dist2 = bfs(board, s, t2);
            
            if (dist1 < dist2) {
                total += dist1 + bfs(board, t1, t2) + 2; // + enter 2
                s = t2; // 출발점 갱신!!
            }
            else {
                total += dist2 + bfs(board, t2, t1) + 2;
                s = t1;
            }
            board[t1.x][t1.y] = 0;
            board[t2.x][t2.y] = 0;
        }
        return total;
    }
    
    int bfs(int[][] board, Pair start, Pair target) {
        Queue<int[]> q = new ArrayDeque<>();
        int[][] visited = new int[N][N];
        q.add(new int[]{start.x, start.y, 0});
        visited[start.x][start.y] = 1;
        
        while (!q.isEmpty()) {
            int[] cur = q.remove();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];
            if (x == target.x && y == target.y) {
                return dist;
            }
            
            for (int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (OOB(nx, ny) || visited[nx][ny] == 1) continue;
                q.add(new int[]{nx, ny, dist+1});
                visited[nx][ny] = 1;
            }
            
            for (int i=0; i<4; i++) {
                Pair next = move(x, y, i, board);
                int nx = next.x;
                int ny = next.y;
                if (OOB(nx, ny) || visited[nx][ny] == 1) continue;
                q.add(new int[]{nx, ny, dist+1});
                visited[nx][ny] = 1;
            }
            
        }
        return -1;
    }
    
    Pair move(int r, int c, int d, int[][] board) {
        while (true) {
            int nr = r+dx[d];
            int nc = c+dy[d];
            if (OOB(nr, nc)) {
                return new Pair(r, c);
            }
            if (board[nr][nc] > 0) {
                return new Pair(nr, nc);
            }
            r = nr;
            c = nc;
        }
        
    }
    
    boolean OOB(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }
}
