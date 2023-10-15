import java.util.*;
import java.io.*;

class Egg {
	int d;
	int w;

	public Egg(int d, int w) {
		this.d = d;
		this.w = w;
	}
}

public class Main {
	static int N;
	static Egg[] eggs;
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		eggs = new Egg[N];

		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		ans = 0;
		dfs(0, 0);

		System.out.println(ans);
	}

	private static void dfs(int left, int cnt) {
		if (left == N || cnt >= N-1) { // 1)최근에 든 계란이 가장 오른쪽에 위치한 계란일 경우, 2)깰 계란이 없는 경우
			ans = Math.max(cnt, ans);
			return;
		}

		if (eggs[left].d <= 0) { // 왼쪽 계란이 깨진경우 건너뛰기
			dfs(left+1, cnt);
			return;
		}

		for (int right=0; right<N; right++) {
			if (right == left) continue; // 자기 자신
			if (eggs[right].d <= 0) continue; // 이미 깬 계란

			// 계란 치기
			eggs[left].d -= eggs[right].w;
			eggs[right].d -= eggs[left].w;

			if (eggs[left].d <= 0) cnt++;
			if (eggs[right].d <= 0) cnt++;

			dfs(left+1, cnt);

			// 되돌리기
			if (eggs[left].d <= 0) cnt--;
			if (eggs[right].d <= 0) cnt--;

			eggs[left].d += eggs[right].w;
			eggs[right].d += eggs[left].w;
		}
	}

}
/*

1. i=0부터 시작
2. 계란 치기
	- 상대의 w만큼 d가 깎임
	- d <= 0 -> 계란 깨짐
	- 왼쪽 계란이 깨지거나 or 더이상 깰 계란 없음 종료
	- 어떤 계란을 취하느냐? -> 완탐
3. i++

 */
