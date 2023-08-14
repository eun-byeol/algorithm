import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int num = 0;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        
        int M = (int)Math.pow(2, N);
        recursive(M, r, c);
        System.out.println(num);
    }

    private static void recursive(int size, int r, int c) {
    	if (size == 1) {
    		return;
    	}
    	size /= 2;
    	if (r < size && c < size) {
    		recursive(size, r, c);
    	}
    	else if (r < size && c >= size) {
    		num += size * size;
    		recursive(size, r, c - size);
    	}
    	else if (r >= size && c < size) {
    		num += size * size * 2;
    		recursive(size, r - size, c);
    	}
    	else {
    		num += size * size * 3;
    		recursive(size, r - size, c - size);
    	}
    }
}
