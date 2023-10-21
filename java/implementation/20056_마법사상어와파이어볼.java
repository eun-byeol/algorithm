import java.io.*;
import java.util.*;

class Ball {
	int row;
	int col;
	int cnt;
	int mass;
	int speed;
	int dir;
	int isSameDir; // 1: 홀수 2: 짝수 3: 불일치

	public Ball() {
		this.row = 0;
		this.col = 0;
		this.cnt = 0;
		this.mass = 0;
		this.speed = 0;
		this.dir = 0;
		this.isSameDir = 0;
	}

	public Ball(int row, int col, int cnt, int mass, int speed, int dir) {
		this.row = row;
		this.col = col;
		this.cnt = cnt;
		this.mass = mass;
		this.speed = speed;
		this.dir = dir;
		this.isSameDir = 0;
	}

	@Override
	public String toString() {
		return "Ball{" +
			"row=" + row +
			", col=" + col +
			", cnt=" + cnt +
			", mass=" + mass +
			", speed=" + speed +
			", dir=" + dir +
			", isSameDir=" + isSameDir +
			'}';
	}
}

public class Main_20056_마법사상어와파이어볼 {
	static int N, M, K;
	static Ball[][] balls;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}; // 파이어볼 방향
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		initBalls();
		Queue<Ball> ready = new ArrayDeque<>();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			ready.add(new Ball(r, c, 1, m, s, d));
		}

		for (int i=0; i<K; i++) {
			while (!ready.isEmpty()) {
				move(ready.remove()); // 1. 파이어볼 이동
			}
			ready = partition(); // 2. 파이어볼 분할
		}

		int ans = 0;
		while (!ready.isEmpty()) {
			ans += ready.remove().mass;
		}
		System.out.println(ans);
	}

	private static void divide(int r, int c, Queue<Ball> ready) {
		Ball ball = balls[r][c];
		int m = ball.mass / 5;
		int s = ball.speed / ball.cnt;

		if (m == 0) return; // 질량이 0인 경우 소멸

		if (ball.isSameDir == 3) { // 방향이 모두 홀or짝이 아닌 경우
			ready.add(new Ball(r, c, 1, m, s, 1));
			ready.add(new Ball(r, c, 1, m, s, 3));
			ready.add(new Ball(r, c, 1, m, s, 5));
			ready.add(new Ball(r, c, 1, m, s, 7));
		}
		else { // 방향이 모두 홀짝인 경우
			ready.add(new Ball(r, c, 1, m, s, 0));
			ready.add(new Ball(r, c, 1, m, s, 2));
			ready.add(new Ball(r, c, 1, m, s, 4));
			ready.add(new Ball(r, c, 1, m, s, 6));
		}
	}

	private static Queue<Ball> partition() {
		Queue<Ball> ready = new ArrayDeque<>();
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (balls[i][j].cnt == 0) continue;
				if (balls[i][j].cnt >= 2) { // cnt >= 2
					divide(i, j, ready);
				}
				else { // cnt == 1
					ready.add(new Ball(i, j, 1, balls[i][j].mass, balls[i][j].speed, balls[i][j].dir));
				}
				balls[i][j] = new Ball(); // 초기화
			}
		}
		return ready;
	}

	private static void move(Ball ball) {
		int nx = (ball.speed * N + ball.row + dx[ball.dir] * ball.speed) % N;
		int ny = (ball.speed * N + ball.col + dy[ball.dir] * ball.speed) % N;
		balls[nx][ny].row = nx;
		balls[nx][ny].col = ny;
		balls[nx][ny].cnt++;
		balls[nx][ny].mass += ball.mass;
		balls[nx][ny].speed += ball.speed;
		balls[nx][ny].dir += ball.dir;
		if (balls[nx][ny].isSameDir == 1) { // 홀수이면
			if (ball.dir % 2 == 0) {
				balls[nx][ny].isSameDir = 3; // 불일치
			}
		}
		else if (balls[nx][ny].isSameDir == 2) { // 짝수이면
			if (ball.dir % 2 != 0) {
				balls[nx][ny].isSameDir = 3; // 불일치
			}
		}
		else if (balls[nx][ny].isSameDir == 0) { // 처음 넣는 공이면
			balls[nx][ny].isSameDir = (ball.dir % 2 != 0) ? 1 : 2;
		}
	}

	private static void initBalls() {
		balls = new Ball[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				balls[i][j] = new Ball();
			}
		}
	}
}


/*

1. 파이어볼 이동
	- ready 큐에서 한개씩 빼서 진행
    - 방향 d로 속력 s 만큼 이동
        dx = {-1, -1, 0, 1, 1, 1, 0, -1}
        dy = {0, 1, 1, 1, 0, -1, -1, -1}
    - 같은 칸에 여러 파이어볼 위치 가능(balls[][] 배열에는 누적값 저장하기)
    - isSameDir : 1 - 모두 홀수, 2 - 모두 짝수, 3 - 방향 불일치

2. 파이어볼 분할
    1) 2개 이상인 경우
    	- 질량 = floor(총질량/5)
        - 속력 = floor(총속력/합쳐진 파이어볼 개수)
        - 방향 = 합쳐진 볼 방향이 모두 홀수 or 짝수 -> 방향 = 0, 2, 4, 6
        		그렇지 않으면 -> 방향 = 1, 3, 5, 7
        - 질량 0 파이어볼은 분할하지 않음
        - 분할한 파이어볼은 ready 큐에 저장
    2) 1개만 있는 경우

3. K번 반복

*/
