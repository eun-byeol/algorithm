import java.util.*;
import java.io.*;

class Seat implements Comparable<Seat>{
	int favoriteCnt;
	int emptyCnt;
	int r;
	int c;
	
	public Seat(int favoriteCnt, int emptyCnt, int r, int c) {
		super();
		this.favoriteCnt = favoriteCnt;
		this.emptyCnt = emptyCnt;
		this.r = r;
		this.c = c;
	}

	@Override
	public int compareTo(Seat s) {
		if (this.favoriteCnt != s.favoriteCnt) {
			return s.favoriteCnt - this.favoriteCnt;
		}
		if (this.emptyCnt != s.emptyCnt) {
			return s.emptyCnt - this.emptyCnt;
		}
		if (this.r != s.r) {
			return this.r - s.r;
		}
		return this.c - s.c;
	}

	@Override
	public String toString() {
		return "Seat [favoriteCnt=" + favoriteCnt + ", emptyCnt=" + emptyCnt + ", r=" + r + ", c=" + c + "]";
	}
	
}

public class Main {
	static int N;
	static int[][] classroom;
	static Map<Integer, List<Integer>> favorite;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int[] point = {0, 1, 10, 100, 1000};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		classroom = new int[N][N];
		favorite = new HashMap<>();
		
		int M = N*N;
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int stuNum = Integer.parseInt(st.nextToken());
			
			List<Integer> list = new ArrayList<>();
			for (int j=0; j<4; j++) {
				list.add(Integer.parseInt(st.nextToken()));				
			}
			favorite.put(stuNum, list);
			
			arrange(stuNum, list);
		}
		
		System.out.println(getScore());
		
	}

	private static int getScore() {
		int score = 0;
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				int cnt = 0;
				for (int d=0; d<4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

					for (Integer num : favorite.get(classroom[i][j])) {
						if (classroom[nx][ny] == num) {
							cnt++;
							break;
						}
					}
				}
				score += point[cnt];
			}
		}
		return score;
	}

	private static void arrange(int stuNum, List<Integer> list) {
		PriorityQueue<Seat> pq = new PriorityQueue<>();

		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (classroom[i][j] == 0) {
					Seat seat = new Seat(0, 0, i, j);
					count(seat, list);
					pq.add(seat);
				}
			}
		}

		Seat result = pq.poll();
		classroom[result.r][result.c] = stuNum;
	}

	private static void count(Seat seat, List<Integer> list) {
		for (int d=0; d<4; d++) {
			int nx = seat.r + dx[d];
			int ny = seat.c + dy[d];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
			
			if (classroom[nx][ny] == 0) {
				seat.emptyCnt++;
				continue;
			}
			
			for (Integer num : list) {
				if (classroom[nx][ny] == num) {
					seat.favoriteCnt++;
					break;
				}
			}
		}
	}

}
