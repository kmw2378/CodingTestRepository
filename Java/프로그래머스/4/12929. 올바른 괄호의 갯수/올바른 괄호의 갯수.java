import java.util.*;

class Solution {
    Set<String> set = new HashSet<>();
    public int solution(int n) {
        backtracking(n, "", 0, 0);
        return set.size();
    }
    
    private void backtracking(int n, String expr, int open, int close) {
        if (open == n && close == n) {
            if (!set.contains(expr)) {
                set.add(expr);
            }
            
            return;
        }
        
        if (open < n) {
            backtracking(n, expr + "(", open + 1, close);
        }
        
        if (close < open) {
            backtracking(n, expr + ")", open, close + 1);
        }
    }
}