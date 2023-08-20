import java.util.*;
import java.io.*;

public class Main {
	static String[] alphabet = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int size = N-M+1;
		Integer[] arr = new Integer[size];
		
		for (int i=0; i<size; i++) {
			arr[i] = M+i;
		}
		
		Arrays.sort(arr, (o1, o2) -> {
			return change(o1).compareTo(change(o2));
		});
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<size; i++) {
			sb.append(arr[i]).append(" ");
			if ((i+1) % 10 == 0)
				sb.append("\n");
		}
		System.out.println(sb);
	}

	private static String change(Integer num) {
		if (num > 9) {
			return alphabet[num/10] + alphabet[num%10];
		}
		return alphabet[num%10];
	}
}
