import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        new Solution().solution(n);
    }
}
class Solution {
    public void solution(int n) {
        backtracking(0, n);
    }
    
    private void backtracking(int current, int attempt) {
        if (attempt == 0) {
            if (isPrime(current)) {
                System.out.println(current);
            }
            return;
        }
        
        for (int i = 0; i < 10; i++) {
            int next = current * 10 + i;
            if (isPrime(next)) {
                backtracking(next, attempt - 1);
            }
        }
    }
    
    private boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        
        for (int i = 2; i <= (int)Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}