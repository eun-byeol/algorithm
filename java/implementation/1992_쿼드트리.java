import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		char[][] movie = new char[N][N];
		
		for (int i=0; i<N; i++) {
			movie[i] = br.readLine().toCharArray();
		}
		
		String ans = dfs(N, 0, 0, movie);
		System.out.println(ans);
	}

	private static String dfs(int size, int x, int y, char[][] movie) {
		if (size == 1) {
			return Character.toString(movie[x][y]);
		}
		
		size /= 2;
		String leftUp = dfs(size, x, y, movie);
		String rightUp = dfs(size, x, y+size, movie);
		String leftDown = dfs(size, x+size, y, movie);
		String rightDown = dfs(size, x+size, y+size, movie);
		
		if (leftUp.length() == 1 && leftUp.equals(rightUp) && leftUp.equals(leftDown) && leftUp.equals(rightDown)) {
			return leftUp;
		}
		return "(" + leftUp + rightUp + leftDown + rightDown + ")";
	}
}
