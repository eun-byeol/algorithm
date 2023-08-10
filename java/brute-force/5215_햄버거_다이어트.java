import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int L;
	static int[] score;
	static int[] cal;
	static int maxScore;
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			score = new int[N];
			cal = new int[N];
			
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				score[i] = Integer.parseInt(st.nextToken());
				cal[i] = Integer.parseInt(st.nextToken());
			}
			
			maxScore = 0;
			combi(0, 0, 0);
			
			sb.append("#")
				.append(tc)
				.append(" ")
				.append(maxScore)
				.append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static void combi(int totalCal, int totalScore, int at) {
		if (totalCal > L)
			return;
		
		if (totalScore > maxScore) // Math.max() 비교하는 것보다 빠르다
			maxScore = totalScore;
		
		for (int i=at; i<N; i++) {
			combi(totalCal + cal[i], totalScore + score[i], i+1);
		}
	}
}
