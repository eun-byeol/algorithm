import java.util.*;
import java.io.*;

public class Main {
	static int L, C;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
        
		char[] alpha = new char[C];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<C; i++) {
			alpha[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(alpha);
		
		sb = new StringBuilder();
		dfs(0, 0, 0, 0, new char[L], alpha);
		
		System.out.println(sb);
	}

	private static void dfs(int depth, int at, int mo, int ja, char[] rst, char[] alpha) {
		if (depth == L) {
			if (mo > 0 && ja > 1)
				sb.append(String.valueOf(rst)).append("\n");
			return;
		}
		
		for (int i=at; i<C; i++) {
			rst[depth] = alpha[i];
			if (isMoeum(alpha[i]))
				dfs(depth+1, i+1, mo+1, ja, rst, alpha);
			else
				dfs(depth+1, i+1, mo, ja+1, rst, alpha);
		}
	}

	private static boolean isMoeum(char v) {
		return v == 'a' || v == 'e' || v == 'i' || v == 'o' || v == 'u';
	}
}
