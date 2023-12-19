import java.util.*;

class Pair {
    int x;
    int y;
    
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public String toString() {
        return "("+ x + "," + y + ")";
    }
}

class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    int N;
    List<List<Pair>>[] pieces = new List[7];
    
    public int solution(int[][] game_board, int[][] table) {
        N = game_board.length;
        int answer = 0;
        
        for (int i=0; i<7; i++) {
            pieces[i] = new ArrayList<>();
        }
        
        findPieces(table); // 조각 인덱스 정보 찾기
        
        answer = check(game_board);
        
        for (int i=0; i<3; i++) {
            int[][] board = rotate(game_board); // board 90도 회전
            answer += check(board); // 일치하는 조각 있는지 확인하기
            game_board = board;
        }
        
        return answer;
    }
    
    int check(int[][] board) {
        int[][] visited = new int[N][N];
        int total = 0;
        
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (board[i][j] == 0 && visited[i][j] == 0) {
                    List<Pair> blank = bfs(i, j, visited, board, 0);
                    int cnt = blank.size();
                    
                    // pieces와 맞는지 확인
                    for (List<Pair> piece : pieces[cnt]) {
                        if (isSame(piece, blank)) {
                            pieces[cnt].remove(piece); // 조각 제거
                            total += cnt;
                            removeBlank(blank, board);
                            break;
                        }
                    }
                }
            }
        }
        return total;
    }
    
    void removeBlank(List<Pair> blank, int[][] board) {
        for (Pair p : blank) {
            board[p.x][p.y] = 1;
        }
    }
    
    boolean isSame(List<Pair> piece, List<Pair> blank) {
        Pair sp = piece.get(0);
        Pair sb = blank.get(0);
        for (int i=0; i<piece.size(); i++) {
            Pair np = piece.get(i);
            Pair nb = blank.get(i);
            if (np.x - sp.x != nb.x - sb.x || np.y - sp.y != nb.y - sb.y) return false;
        }
        return true;
    }
    
    int[][] rotate(int[][] board) {
        int[][] result = new int[N][N];
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                result[j][N-i-1] = board[i][j];
            }
        }
        return result;
    }
    
    void findPieces(int[][] table) {
        int[][] visited = new int[N][N];
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (table[i][j] == 1 && visited[i][j] == 0) {
                    List<Pair> piece = bfs(i, j, visited, table, 1);
                    pieces[piece.size()].add(piece);
                }
            }
        }
    }
    
    List<Pair> bfs(int sx, int sy, int[][] visited, int[][] table, int target) {
        List<Pair> piece = new ArrayList<>();
        Queue<Pair> q = new ArrayDeque<>();
        Pair start = new Pair(sx, sy);
        piece.add(start);
        q.add(start);
        visited[sx][sy] = 1;
        
        while(!q.isEmpty()) {
            Pair cur = q.remove();
            for (int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (OOB(nx, ny) || table[nx][ny] != target || visited[nx][ny] == 1) continue;
                Pair next = new Pair(nx, ny);
                q.add(next); 
                piece.add(next);
                visited[nx][ny] = 1;
            }
        }
        return piece;
    }
    
    boolean OOB(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }
}

/*

1. table에서 조각의 좌표를 List[]에 저장
- List<List<pair>>[] = new List[7];

2. game_board 90도 회전하기
- board[j][N-i] = game_board[i][j];

3. game_board에 맞는 조각이 있는지 확인하기
1) bfs를 돌면서 -> 칸을 센다 -> x,y 좌표가 가장 작은 칸이 기준이 된다.
    - visited 체크해줘야 한다.
2) bfs 반환값과 table 조각의 정보 중 일치하는 것이 있는지 찾는다.
    - 일치한다면 table 조각을 remove
    - 첫 번째 좌표가 기준이 된다. 첫 번째 좌표를 뺀 상대 좌표로 비교한다.

4. 채워진 칸 세기
- 일치한 조각의 개수 누적해준다.

*/
