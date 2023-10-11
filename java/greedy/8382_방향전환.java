import java.util.*;
import java.io.*;

public class Solution_8382_방향전환 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int dx = Math.abs(x1 - x2);
			int dy = Math.abs(y1 - y2);
			int remain = Math.abs(dx - dy);
			int move = Math.min(dx, dy) * 2;
			if (remain % 2 == 0) {
				move += 2 * remain;
			}
			else {
				move += 2 * (remain-1) + 1;
			}
			
			sb.append("#").append(tc).append(" ").append(move).append("\n");
		}
		
		System.out.println(sb);
	}

}
