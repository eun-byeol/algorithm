import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
 
public class Solution { 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        for (int tc = 1; tc <= 10; tc++) {
            int N = Integer.parseInt(br.readLine());
             
            String ops = br.readLine();
             
            System.out.printf("#%d %d\n", tc, check(ops, N));
        }
    }
 
    private static Object check(String ops, int n) {
        Stack<Character> stack = new Stack<>();
        for (int i=0; i<n; i++) {
            char op = ops.charAt(i);
            if (op == ')' || op == '>' || op == '}' || op == ']') {
                if (stack.isEmpty() || !isPair(stack.pop(), op)) {
                    return 0;
                }
            }
            else {
                stack.add(op);
            }
        }
        if (stack.isEmpty()) {
            return 1;
        }
        return 0;
    }
 
    private static boolean isPair(char op1, char op2) {
        if (op1 == '(') {
            return op2 == ')';
        }
        if (op1 == '<') {
            return op2 == '>';
        }
        if (op1 == '{') {
            return op2 == '}';
        }
        if (op1 == '[') {
            return op2 == ']';
        }
        return false;
    }
}
