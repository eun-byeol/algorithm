import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] sushi = new int[N];
		
		for (int i=0; i<N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		
		int[] cnts = new int[3001];
		int maxAns = 0;
		int total = 0;
		
		// 첫 번째 경우
		for (int i=0; i<k; i++) {
			cnts[sushi[i]]++;
			if (cnts[sushi[i]] == 1) {
				total++;
			}
		}
		
		if (cnts[c] == 0) {
			maxAns = Math.max(total+1, maxAns);
		}
		else {
			maxAns = Math.max(total, maxAns);
		}
		
		// 첫번째 이후 경우
		int left = 0;
		int right = k-1;
		
		while (left < N) {
			// 삭제
			int num = sushi[left++];
			cnts[num]--;
			if (cnts[num] < 1) {
				cnts[num] = 0;
				total--;
			}
			
			// 추가
			right = (right+1) % N;
			num = sushi[right];
			cnts[num]++;
			if (cnts[num] == 1) {
				total++;
			}
			
			if (cnts[c] == 0) {
				maxAns = Math.max(total+1, maxAns);
			}
			else {
				maxAns = Math.max(total, maxAns);
			}
		}
		
		System.out.println(maxAns);
	}
}
