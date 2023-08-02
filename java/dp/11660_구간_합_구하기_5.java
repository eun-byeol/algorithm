import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[N+1][N+1];
		
		for (int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<N+1; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i=1; i<N+1; i++) {
			for (int j=1; j<N+1; j++) {
				board[i][j] += board[i-1][j] + board[i][j-1] - board[i-1][j-1];
			}
		}
		
		for (int k=0; k<M; k++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int total = board[x2][y2] - board[x1-1][y2] - board[x2][y1-1] + board[x1-1][y1-1];
			sb.append(total).append("\n");
		}
		System.out.println(sb.toString());
	}
}
