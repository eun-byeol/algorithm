import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14499_주사위굴리기 {
    static int[] dx = {0, 0, -1, 1}; // 동서북남
    static int[] dy = {1, -1, 0, 0};
    static int[][] diceDir = {
        {0, 2, 3, 5, 4, 1},
        {0, 5, 1, 2, 4, 3},
        {5, 1, 0, 3, 2, 4},
        {2, 1, 4, 3, 5, 0}
    };
    static int N, M, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] curIdx = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        K = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dice = {0, 0, 0, 0, 0, 0};
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<K; i++) {
            int dir = Integer.parseInt(st.nextToken()) - 1; // 0부터 시작(동서북남)
            int topNum = simul(dir, curIdx, board, dice);
            if (topNum != -1) { // 정상적인 이동인 경우만 결과 출력
                sb.append(topNum).append("\n");
            }
        }
        System.out.print(sb);
    }

    private static int simul(int dir, int[] curIdx, int[][] board, int[] dice) {
        // 다음 위치 찾기
        int nx = curIdx[0] + dx[dir];
        int ny = curIdx[1] + dy[dir];
        if (OOB(nx, ny)) { // 이동 불가
            return -1;
        }
        curIdx[0] = nx;
        curIdx[1] = ny;

        // 주사위 돌리기
        int[] tmp = dice.clone();
        for (int i=0; i<6; i++) {
            dice[i] = tmp[diceDir[dir][i]];
        }
        
        // 숫자 복사
        if (board[nx][ny] == 0) {
            board[nx][ny] = dice[2]; // 주사위 bottom
        }
        else {
            dice[2] = board[nx][ny];
            board[nx][ny] = 0;
        }
        return dice[5]; // 주사위 top
    }

    private static boolean OOB(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }
}
