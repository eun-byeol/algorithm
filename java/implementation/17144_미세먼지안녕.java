import java.io.*;
import java.util.*;

public class Main_17144_미세먼지안녕 {
	static int R, C, T;
	static int[][] board;
	static int[][] upDir = {{-1,0}, {0,1}, {1,0}, {0,-1}};
	static int[][] downDir = {{1,0}, {0,1}, {-1,0}, {0,-1}};
	static int[] air;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		board = new int[R][C];
		air = new int[] {-1, -1};
		
		for (int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<C; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (air[0] == -1 && board[i][j] == -1) {
					air[0] = i;
					air[1] = j;
				}
			}
		}
		
		
		for (int i=0; i<T; i++) {
			virus();	
			cleanUp(air[0], air[1]);
			cleanDown(air[0]+1, air[1]);
		}
		
		int total = 0;
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				if (board[i][j] > 0) {
					total += board[i][j];
				}
			}
		}
		
		System.out.println(total);
	}
	
	private static void cleanUp(int airX, int airY) {
		int x = airX-1;
		int y = airY;
		
		for(int d=0; d<4; d++) {
			while (x != airX || y != airY+1) {
				int nx = x + upDir[d][0];
				int ny = y + upDir[d][1];
				if (nx < 0 || nx > airX || ny < 0 || ny >= C) break;
				board[x][y] = board[nx][ny];
				x = nx;
				y = ny;
			}
		}
		board[airX][airY+1] = 0;
	}
	
	private static void cleanDown(int airX, int airY) {
		int x = airX+1;
		int y = airY;
		
		for(int d=0; d<4; d++) {
			while (x != airX || y != airY+1) {
				int nx = x + downDir[d][0];
				int ny = y + downDir[d][1];
				if (nx < airX || nx >= R || ny < 0 || ny >= C) break;
				board[x][y] = board[nx][ny];
				x = nx;
				y = ny;
			}
		}
		board[airX][airY+1] = 0;
	}

	private static void virus() {
		int[][] pre = copyBoard();

		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				if (pre[i][j] <= 0) continue;
				int cnt = 0;
				int amount = pre[i][j] / 5;
				
				for (int d=0; d<4; d++) {
					int nx = i + upDir[d][0];
					int ny = j + upDir[d][1];
					if (nx < 0 || nx >= R || ny < 0 || ny >= C || pre[nx][ny] == -1) continue; // 공기청정기이거나 칸을 넘는 경우
					cnt++;
					board[nx][ny] += amount;
				}
				board[i][j] -= amount*cnt;
			}
		}
	}
	
	private static int[][] copyBoard() {
		int[][] cp = new int[R][C];
		for (int i=0; i<R; i++) {
			cp[i] = board[i].clone();
		}
		return cp;
	}
}
