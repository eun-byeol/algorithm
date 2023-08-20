import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String p = br.readLine();
		int n = p.length();
		int value = 1;
		int total = 0;
		Stack<Character> stack = new Stack<>();
		
		for (int i=0; i<n; i++) {
			char op = p.charAt(i);
			// 열린 괄호 -> 곱하기 연산
			if (op == '(') {
				value *= 2;
				stack.push(op);
			}
			else if (op == '[') {
				value *= 3;
				stack.push(op);				
			}
			// 닫힌 괄호 -> 더하기 연산, 값 갱신
			else if (op == ')') {
				if (stack.isEmpty() || stack.peek() != '(') { // 잘못된 괄호 
					total = 0;
					break;
				}
				if (p.charAt(i-1) == '(') {
					total += value;
				}
				value /= 2;
				stack.pop();
			}
			else if (op == ']') {
				if (stack.isEmpty() || stack.peek() != '[') {
					total = 0;
					break;
				}
				if (p.charAt(i-1) == '[') {
					total += value;
				}
				value /= 3;
				stack.pop();
			}
		}
		if (!stack.isEmpty()) {
			total = 0;
		}
		System.out.println(total);
	}
}
