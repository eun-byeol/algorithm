import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String ops = br.readLine();
        Stack<Character> stack = new Stack<>();
        boolean flag = false;
        int ans = 0;
        
        for (int i=0; i<ops.length(); i++) {
        	char op = ops.charAt(i); 
        	if (op == '(') {
        		stack.push(op);
        		flag = true;
        	}
        	else {
        		stack.pop();
        		if (flag) {
        			ans += stack.size();
        			flag = false;
        		}
        		else {
        			ans++;
        		}
        	}
        }
        ans += stack.size();
        
        System.out.println(ans);
    }
}
