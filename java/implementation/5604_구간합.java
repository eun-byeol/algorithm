import java.io.*;
import java.util.*;

public class Solution_5604_구간합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long num1 = Long.parseLong(st.nextToken());
			long num2 = Long.parseLong(st.nextToken());
			long[] cnt = new long[10]; // 0 ~ 9 등장 횟수 담는 배열
			long ans = 0L;
			long point = 1;

			while (num1 <= num2) {
				// 일의자리수가 0이 될 때까지 하나씩 계산
				while (num1 % 10 > 0 && num1 <= num2) {
					cal(num1, cnt, point);
					num1++;
				}

				if (num1 > num2) break; // 계산 끝

				// 일의자리수가 9이 될 때까지 하나씩 계산
				while (num2 % 10 < 9 && num1 <= num2) {
					cal(num2, cnt, point);
					num2--;
				}

				// 0 ~ 9 단위 묶음으로 계산
				num1 /= 10;
				num2 /= 10;
				for (int i=0; i<10; i++) {
					cnt[i] += (num2 - num1 + 1) * point;
				}
				point *= 10; // 자릿수 한자리 높이기

				if (num1 == 0 && num2 == 0) break; // 계산 끝
			}

			for (int i=1; i<10; i++) { // 카운트 총합 구하기
				ans += cnt[i] * i;
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}

	private static void cal(long num, long[] cnt, long point) { // 모든 자릿수 계산
		long tmp = num;
		while (tmp > 0) {
			cnt[(int) (tmp % 10)] += point;
			tmp /= 10;
		}
	}
}

/*
디버깅 : num1 == num2 == 0이 될 때 무한 루프에 빠짐

실패 요인 : 자료형!!
	- 카운트 배열(cnt), point 또한 long이 되어야 함!
 */
