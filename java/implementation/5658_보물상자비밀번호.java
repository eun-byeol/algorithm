import java.util.*;
import java.io.*;
public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int size = N / 4;
			String pwd = br.readLine();
			
			TreeSet<Integer> nums = new TreeSet<>(Collections.reverseOrder());
			
			for (int i=0; i<N; i++) { // 1. 모든 경우의 숫자들 구하기
				if (i+size <= N) { // end가 인덱스 범위를 넘지 않는 경우
					String num = pwd.substring(i, i+size);
					nums.add(Integer.parseInt(num, 16));
				}
				else { // end가 인덱스 범위를 넘는 경우
					String num = pwd.substring(i, N) + pwd.substring(0, size - (N-i));
					nums.add(Integer.parseInt(num, 16));
				}	
			}

			int ans = 0;
			for (int i=0; i<K; i++) { // 2. K번째 숫자 구하기
				if (!nums.isEmpty()) {
					ans = nums.pollFirst();
				}
			}
			
			sb.append("#")
				.append(tc)
				.append(" ")
				.append(ans)
				.append("\n");
		}
		System.out.println(sb);
	}
}
