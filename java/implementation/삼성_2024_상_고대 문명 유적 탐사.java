import java.util.*;
import java.io.*;

public class Main {
    static int K, M;
    static int[][] board = new int[5][5];
    static int[] nums;
    static int[][] centers = {{1,1}, {1,2}, {1,3}, {2,1}, {2,2}, {2,3}, {3,1}, {3,2}, {3,3}};
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int numsI = 0;
    static int kNumsI = 0;
    static int maxNumsI = 0;

    public static void main(String[] args) throws Exception {
        init();
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<K; i++) {
            int result = run();
            
            if (result == 0) {
                break;
            }
            sb.append(result).append(" ");
        }
        System.out.println(sb);
    }

    static int run() {
        int maxV = 0;
        int[][] maxB = board;
        for (int i=1; i<=3; i++) { // 90, 180, 270 회전
            for (int c = 0; c < 3; c++) {
                for (int r = 0; r < 3; r++) {
                    numsI = kNumsI; // 초기화
                    int[][] rotated = rotate(i, r, c);
                    int firstV = simul(rotated);
                    if (firstV > maxV) {
                        maxV = firstV;
                        maxB = rotated;
                        maxNumsI = numsI;
                    }
                }
            }
        }
        numsI = maxNumsI;

        while (true) {
            int v = simul(maxB);
            if (v == 0) break;
            maxV += v;
        }
        kNumsI = numsI;
        board = maxB;
        return maxV;
    }

    static int simul(int[][] board) {
        int total = 0;
        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
                if (board[i][j] == 0) continue;
                total += bfs(i, j, board); // 0으로 바꿔놨다가 다른 숫자로 교체
            }
        }

        for (int j=0; j<5; j++) { // 유물 채우기
            for (int i=4; i>=0; i--) {
                if (board[i][j] == 0) {
                    board[i][j] = nums[numsI];
                    numsI = (numsI+1) % M;
                }
            }
        }
        return total;
    }

    static int bfs(int sr, int sc, int[][] board) {
        int number = board[sr][sc];
        int cnt = 1;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sr, sc});
        board[sr][sc] = 0;
        List<int[]> group = new LinkedList<>();
        group.add(new int[]{sr, sc});
    
        while(!q.isEmpty()) {
            int[] cur = q.remove();
            int r = cur[0];
            int c = cur[1];

            for (int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (OOB(nr, nc) || board[nr][nc] != number) continue;
                q.add(new int[]{nr, nc});
                board[nr][nc] = 0;
                cnt++;
                group.add(new int[]{nr, nc});
            }
        }
        if (cnt < 3) {
            for (int[] idx : group) {
                board[idx[0]][idx[1]] = number;
            }
            return 0;
        }
        return cnt;
    }

    static boolean OOB(int r, int c) {
        return r < 0 || r >= 5 || c < 0 || c >= 5;
    }

    static int[][] rotate(int turnCnt, int r, int c) {
        int[][] copy = new int[5][5];
        for (int i=0; i<5; i++) {
            copy[i] = board[i].clone();
        }
        int[][] orders = {{0,0}, {0,1}, {0,2}, {1,2}, {2,2}, {2,1}, {2,0}, {1,0}};
        int J = 2 * turnCnt; // 90도면 2칸, 180이면 4칸, 270이면 6칸 점프

        for (int o = 0; o < 8; o++) {
            int t = (o + J) % 8;
            int tr = r + orders[t][0];
            int tc = c + orders[t][1];
            int or = r + orders[o][0];
            int oc = c + orders[o][1];
            copy[tr][tc] = board[or][oc];
        }
        return copy;
    }

    static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i=0; i<5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<5; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
		
        nums = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<M; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
    }
}

/*

1. 탐사
- 3*3 격자 선택
- 90, 180, 270 회전 가능

2. 유물 획득
- 상하좌우 같은 번호 3개 이상 -> count, 사라짐
- row 큰 > col 작은 순서대로
- 연쇄 획득(더 없어질때까지 반복)

3. 탐사 반복
- K번 반복
- 각 턴마다 유물 가치 총합 출력
- 추가 유물 획득 불가능할 시 -> 조기 종료
- 이후 턴에는 출력값 없음

<답>
1. 유물 1차 획득 가치 최대화
2. 회전각 최소
3. 회전 중심 좌표 col 최소
4. 회전 중심 좌표 row 최소
*/
