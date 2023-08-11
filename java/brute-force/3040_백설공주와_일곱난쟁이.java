import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = 9;
		int[] nums = new int[N];
		
		int total = 0;
		for (int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
			total += nums[i];
		}
		
		exit :
		for (int i=0; i<N-1; i++) {
			for (int j=i+1; j<N; j++) {
				if (nums[i] + nums[j] == total - 100) {
					nums[i] = 0;
					nums[j] = 0;
					break exit; 
				}
			}
		}
		
		for (int i=0; i<N; i++) {
			if (nums[i] > 0)
				System.out.println(nums[i]);
		}
	}
}
