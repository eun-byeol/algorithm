import java.util.*;
import java.io.*;

public class Solution_8458_원점으로집합 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int maxDist = Math.abs(x) + Math.abs(y);
			
			int ans = 0;
			for (int i=0; i<N-1; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				int dist = Math.abs(x) + Math.abs(y);
				
				if ((maxDist % 2 == 0)^(dist % 2 ==0)) { // 이동시킬 수 없는 경우
					ans = -1;
				}
				
				maxDist = Math.max(dist, maxDist);
			}
			
			if (ans != -1) {
				ans = 0;
				int i=1;
				while (maxDist > 0) {
					maxDist -= i;
					i++;
					ans++;
				}
				
				maxDist *= -1;
				if (maxDist % 2 == 1) { // 넘친 거리가 홀수인 경우
					if (i % 2 == 0) { // 다음 이동이 짝수이면 제자리 -> +2
						ans += 2;
					}
					else { // 다음 이동이 홀수이면 한 칸 이동 -> +1
						ans += 1;
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}

}
