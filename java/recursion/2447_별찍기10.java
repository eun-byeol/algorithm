import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        boolean[][] board = new boolean[N][N];

        draw(N, 0, 0, board);

        for (boolean[] row : board) {
            for (boolean isStar : row) {
                if (isStar) sb.append("*");
                else sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static void draw(int size, int r, int c, boolean[][] board) {
        if (size == 3) {
            board[r][c] = true;
            board[r][c+1] = true;
            board[r][c+2] = true;
            board[r+1][c] = true;
            board[r+1][c+2] = true;
            board[r+2][c] = true;
            board[r+2][c+1] = true;
            board[r+2][c+2] = true;
            return;
        }

        size /= 3;
        draw(size, r, c, board);
        draw(size, r, c+size, board);
        draw(size, r, c+2*size, board);
        draw(size, r+size, c, board);
        draw(size, r+size, c+2*size, board);
        draw(size, r+2*size, c, board);
        draw(size, r+2*size, c+size, board);
        draw(size, r+2*size, c+2*size, board);
    }
}
