import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] operands = new int[n];
        for (int i = 0; i < n; i++) {
            operands[i] = sc.nextInt();
        }
        
        int[] operators = new int[4];
        for (int i = 0; i < 4; i++) {
            operators[i] = sc.nextInt();
        }
        
        int[] answer = new Solution().solution(operands, operators);
        System.out.println(answer[0]);
        System.out.println(answer[1]);
    }
}
class Solution {
    Map<Integer, Character> operatorMap = Map.of(0, '+', 1, '-', 2, '*', 3, '/');
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    
    public int[] solution(int[] operands, int[] operators) {
        backtracking(1, String.valueOf(operands[0]), operands, operators);
        return new int[]{max, min};
    }
    
    private void backtracking(int idx,
                              String expr,
                              int[] operands,
                              int[] operators) {
        if (idx == operands.length) {
            int result = Calculator.evaluate(expr);
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }
        
        for (int i = 0; i < operators.length; i++) {
            if (operators[i] <= 0) {
                continue;
            }
            
            operators[i]--;
            backtracking(idx + 1, expr + operatorMap.get(i) + operands[idx], operands, operators);
            operators[i]++;
        }
    }
}

class Calculator {
    // 연산자의 우선순위를 반환
    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    // 후위 표기법으로 변환
    private static String toPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        int i = 0;
        
        while (i < expression.length()) {
            char c = expression.charAt(i);
            
            // 숫자라면 (여러 자리 숫자 처리)
            if (Character.isDigit(c)) {
                StringBuilder num = new StringBuilder();
                
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    num.append(expression.charAt(i));
                    i++;
                }
                
                result.append(num).append(" ");
                continue; // 다음 문자로 넘어감
            }
            // 왼쪽 괄호라면 스택에 푸시
            else if (c == '(') {
                stack.push(c);
            } 
            // 오른쪽 괄호라면, 왼쪽 괄호를 만날 때까지 팝
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop()).append(" ");
                }
                stack.pop(); // 왼쪽 괄호 제거
            } 
            // 연산자라면
            else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result.append(stack.pop()).append(" ");
                }
                stack.push(c);
            }
            i++;
        }

        // 남은 연산자 스택에서 팝
        while (!stack.isEmpty()) {
            result.append(stack.pop()).append(" ");
        }
        
        return result.toString();
    }

    // 후위 표기법 계산
    private static int evaluatePostfix(String postfix) {
        Stack<Integer> stack = new Stack<>();
        String[] tokens = postfix.split("\\s+");
        
        for (String token : tokens) {
            // 숫자라면 스택에 푸시
            if (token.matches("\\d+")) {
                stack.push(Integer.parseInt(token));
            } 
            // 연산자라면 스택에서 팝하여 계산 후 결과를 푸시
            else {
                int val2 = stack.pop();
                int val1 = stack.pop();
                
                switch (token) {
                    case "+":
                        stack.push(val1 + val2);
                        break;
                    case "-":
                        stack.push(val1 - val2);
                        break;
                    case "*":
                        stack.push(val1 * val2);
                        break;
                    case "/":
                        stack.push(val1 / val2);
                        break;
                }
            }
        }
        
        return stack.pop();
    }

    public static int evaluate(String expression) {
        String postfix = toPostfix(expression);
        return evaluatePostfix(postfix);
    }
}