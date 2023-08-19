import java.io.*;
import java.util.*;

public class Main {
	static String[] arr = new String[100000]; // 배열 풀 사용
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=0; tc<T; tc++) {
			String p = br.readLine();
			int n = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine(), "[,]");
			
			for (int i=0; i<n; i++) {
				arr[i] = st.nextToken();
			}
			
			int dir = 1;
			int left = 0;
			int right = n-1;
			boolean errorFlag = false;
			
			for (int i=0; i<p.length(); i++) {
				char cmd = p.charAt(i);
				if (cmd == 'R') {
					dir *= -1;
				}
				else if (cmd == 'D') {
					if (left > right) {
						errorFlag = true;
						break;
					}
					if (dir == 1) {
						left++;
					}
					else {
						right--;
					}
				}
			}
			
			if (errorFlag) {
				sb.append("error\n");
				continue;
			}
			
			sb.append("[");
			while (left <= right) {
				if (dir == 1)
					sb.append(arr[left++]);
				else
					sb.append(arr[right--]);
				
				if (left <= right)
					sb.append(",");
			}
			sb.append("]\n");
		}
		System.out.println(sb);
	}
}
