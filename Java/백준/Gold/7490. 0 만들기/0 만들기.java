import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = sc.nextInt();
        Solution s = new Solution();
        for (int i = 1; i <= cnt; i++) {
            int n = sc.nextInt();
            s.solution(n);
            System.out.println();
        }
    }
}
class Solution {
    public void solution(int n) {
        backtracking(2, n, "1");
    }
    
    private void backtracking(int current, int n, String expr) {
        if (current > n) {
            if (expr.matches("\\d+")) {
                return;
            }
            int result = calculate(expr);
            if (result == 0) {
                System.out.println(expr);
            }
            return;
        }
        
         backtracking(current + 1, n, expr + " " + current);
        backtracking(current + 1, n, expr + "+" + current);
        backtracking(current + 1, n, expr + "-" + current);
    }
    
    private int calculate(String expr) {
        String[] arr = toPostfix(expr);
        Stack<Integer> stack = new Stack<>();
        for (String s : arr) {
            if (s.matches("\\d+")) {
                stack.push(Integer.parseInt(s));
            } else {
                int second = stack.pop();
                int first = stack.pop();
                stack.push(calculate(first, s.charAt(0), second));
            }
        }
        
        return stack.peek();
    }
    
    private String[] toPostfix(String s) {
        String expr = s.replaceAll(" ", "");
        Stack<Character> stack = new Stack<>();
        int i = 0;
        StringBuilder result = new StringBuilder();
        while (i < expr.length()) {
            char c = expr.charAt(i);
            if (Character.isDigit(c)) {
                StringBuilder number = new StringBuilder();
                while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
                    number.append(expr.charAt(i));
                    i++;
                }
                result.append(number).append(" ");
                continue;
            } else {
                while (!stack.isEmpty() && getPriority(c) <= getPriority(stack.peek())) {
                    result.append(stack.pop()).append(" ");
                }
                stack.push(c);
            }
            i++;
        }
        
        while (!stack.isEmpty()) {
            result.append(stack.pop()).append(" ");
        }
        
        return result.toString().trim().split(" ");
    }
    
    private int calculate(int x, char op, int y) {
        if (op == '+') {
            return x + y;
        } else if (op == '-') {
            return x - y;
        } else if (op == '*') {
            return x * y;
        } else if (op == '/') {
            return x / y;
        }
        
        throw new IllegalArgumentException("Invalid operator");
    }
    
    private int getPriority(char c) {
        if (c == '+' || c == '-') {
            return 1;
        }
        if (c == '*' || c == '/') {
            return 2;
        }
        
        throw new IllegalArgumentException("Invalid operator");
    }
}