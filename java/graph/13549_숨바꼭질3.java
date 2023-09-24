import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int subin = Integer.parseInt(st.nextToken());
		int sister = Integer.parseInt(st.nextToken());
				
		int ans = bfs(subin, sister);
		System.out.println(ans);
	}

	private static int bfs(int start, int target) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(); 
		int M = Math.max(start, target) * 2;
		int[] dist = new int[M];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		pq.add(start);
		
		while (!pq.isEmpty()) {
			int cur = pq.poll();
			
			if (cur*2 < M && dist[cur] < dist[cur*2]) {
				dist[cur*2] = dist[cur];
				pq.add(cur*2);
			}
			
			if (cur > 0 && dist[cur]+1 < dist[cur-1]) {
				dist[cur-1] = dist[cur]+1;
				pq.add(cur-1);
			}
			
			if (cur+1 < M && dist[cur]+1 < dist[cur+1]) {
				dist[cur+1] = dist[cur]+1;
				pq.add(cur+1);
			}
			
		}
		return dist[target];
	}
}

/*

1. 오답
	- 추가 테케 : 2 7
		=> 수빈==동생일때 바로 멈추면 안 됨. dist[8]+1 < dist[7]이 반영되야 함!
2. 인덱스에러
 	- 수빈 > 동생 위치일 수 있음 주의!
 		=> dist 크기를 max(수빈, 동생)*2로 해줘야 함!

*/
