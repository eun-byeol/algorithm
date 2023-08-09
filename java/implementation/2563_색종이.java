import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] visited = new int[100][100];
		
		int total = 0;
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			for (int r=x; r<x+10; r++) {
				for (int c=y; c<y+10; c++) {
					if (visited[r][c] != 1) {
						total++;
						visited[r][c] = 1;
					}
				}
			}
		}
		System.out.println(total);
	}
}
