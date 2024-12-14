import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] ans = solve(nums, N);

        System.out.println(ans[0] + " " + ans[1]);
    }

    static int[] solve(int[] nums, int N) {
        int minDist = 2_000_000_000;
        int[] ans = new int[2];

        if (nums[0] >= 0) {
            return new int[]{nums[0], nums[1]};
        }
        if (nums[N-1] <= 0) {
            return new int[]{nums[N-2], nums[N-1]};
        }

        int left = 0;
        int right = N-1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            int dist = Math.abs(sum);
            if (minDist > dist) {
                minDist = dist;
                ans[0] = nums[left];
                ans[1] = nums[right];
            }
            if (sum == 0) {
                return ans;
            }
            if (sum < 0) {
                left++;
                continue;
            }
            right--;
        }
        return ans;
    }
}
