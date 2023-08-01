import java.util.*;
import java.io.*;

public class Solution {
	static final int N = 100;
	static int[] boxes = new int[N];
	static int maxIdx;
	static int minIdx;
	
	static void findMaxMin() { // 최대 최소 인덱스 찾기
		maxIdx = 0;
		minIdx = 0;
		for (int i=1; i<N; i++) {
			if (boxes[maxIdx] < boxes[i]) {
				maxIdx = i;
			}
			else if (boxes[minIdx] > boxes[i]) {
				minIdx = i;
			}
		}
	}
	
	static void run(int cnt) { // 메인 로직
		for (int i=0; i<cnt; i++) {
			findMaxMin();
			if (boxes[maxIdx] - boxes[minIdx] <= 1) return;
			boxes[maxIdx]--;
			boxes[minIdx]++;
		}
		findMaxMin();
	}
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = 10;
		
		for(int test_case = 1; test_case <= T; test_case++) {
			
			int dumpCnt =Integer.parseInt(br.readLine()); //입력
			st = new StringTokenizer(br.readLine());
			
			for (int i=0; i<N; i++) {
				boxes[i] = Integer.parseInt(st.nextToken());
			}
			
			run(dumpCnt); //메인 로직
			
			System.out.printf("#%d %d\n", test_case, boxes[maxIdx] - boxes[minIdx]);
		}
	}
}

/*

<로직>
1. 평탄화 수행 반복
	1) boxes에서 최대 최소 인덱스를 찾는다 O(n)
		- 초기화 : maxIdx, minIdx = 0
	2) 상자 옮기기
		- boxes[maxIdx]--
		- boxes[minIdx]++
	3) 평탄화 작업 완료 -> 횟수가 남았어도 종료
		- 조건 : boxes[maxIdx] - boxes[minIdx] <= 1 (가장 높은 곳과 가장 낮은 곳의 차이가 최대 1 이내)

2. 결과
	- 평탄화 수행 종료 후, 최대 최소 인덱스를 다시 찾는다
	
*/
