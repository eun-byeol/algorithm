import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int ansCnt = 0;
        for (int i=0; i<N; i++) {
        	String word = br.readLine();
        	
        	Stack<Character> stack = new Stack<>();
        	
        	for (int j=0; j<word.length(); j++) {
        		char cur = word.charAt(j);
        		if (!stack.isEmpty() && stack.peek() == cur) {
        			stack.pop();
        		}
        		else {
        			stack.push(cur);
        		}
        	}
        	if (stack.isEmpty()) {
        		ansCnt++;
        	}
        }
        
        System.out.println(ansCnt);
    }
}
