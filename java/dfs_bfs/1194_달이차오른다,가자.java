import java.io.*;
import java.util.*;

class Node {
    int x, y, key, dist;
    
    public Node(int x, int y, int key, int dist) {
        super();
        this.x = x;
        this.y = y;
        this.key = key;
        this.dist = dist;
    }

	@Override
	public String toString() {
		return "Node [x=" + x + ", y=" + y + ", key=" + key + ", dist=" + dist + "]";
	}
}
public class Main {
    static int N, M;
    static char[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new char[N][M];
        int sx=0;
        int sy=0;
        for (int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j=0; j<M; j++) {
                if (map[i][j] == '0') {
                    sx = i;
                    sy = j;
                    break;
                }
            }
        }
        
        int ans = bfs(sx, sy);
        
        System.out.println(ans);
    }

    private static int bfs(int sx, int sy) {
        int visited[][][] = new int[64][N][M];
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(sx, sy, 0, 0));
        visited[0][sx][sy] = 1;
        
        while(!q.isEmpty()) {
            Node cur = q.remove();
            
            if (map[cur.x][cur.y] == '1') {
                return cur.dist;
            }
            
            for (int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if (nx < 0 || nx >= N || ny < 0 || ny >=M || visited[cur.key][nx][ny] == 1) continue; // 해당 키를 갖고 이미 방문했다면
                if (map[nx][ny] == '#') continue;
                if ('a' <= map[nx][ny] && map[nx][ny] <= 'f') { // 키를 주운 경우 
                	int nxtKey = cur.key | (1 << (map[nx][ny] - 'a'));
                	q.add(new Node(nx, ny, nxtKey, cur.dist+1));
                	visited[nxtKey][nx][ny] = 1;
                	continue;
                }
                if ('A' <= map[nx][ny] && map[nx][ny] <= 'F') { 
                	if ((cur.key & (1 << (map[nx][ny] - 'A'))) > 0) { // 매칭된 키가 있는 경우
                		q.add(new Node(nx, ny, cur.key, cur.dist+1));
                		visited[cur.key][nx][ny] = 1;
                	}
                	continue;
                }
                q.add(new Node(nx, ny, cur.key, cur.dist+1)); // 이외 이동할 수 있는 경우
                visited[cur.key][nx][ny] = 1;
            }
            
        }
        return -1;
    }

}
