import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int H = Integer.parseInt(br.readLine());
        int B = 2*H-1;
        boolean[][] board = new boolean[H][B];

        draw(H, B, H-1, 0, board);

        for (boolean[] row : board) {
            for (boolean isStar : row) {
                if (isStar) sb.append("*");
                else sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static void draw(int height, int base, int r, int c, boolean[][] board) {
        if (height == 3) {
            board[r][c] = true;
            board[r][c+1] = true;
            board[r][c+2] = true;
            board[r][c+3] = true;
            board[r][c+4] = true;
            board[r-1][c+1] = true;
            board[r-1][c+3] = true;
            board[r-2][c+2] = true;
            return;
        }

        height /= 2;
        base = height*2-1;
        draw(height, base, r, c, board);
        draw(height, base, r-height, c+height, board);
        draw(height, base, r, c+base+1, board);
    }
}
