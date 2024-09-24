import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        int n = sequence.length;
        long[] dp1 = new long[n + 1];
        long[] dp2 = new long[n + 1];
        
        // 절댓값 사용
        for (int i = 1; i <= n; i++) {
            dp1[i] = Math.max(0, dp1[i - 1]) + (i % 2 == 1 ? 1 : -1) * sequence[i - 1];
        }
        
        for (int i = 1; i <= n; i++) {
            dp2[i] = Math.max(0, dp2[i - 1]) + (i % 2 == 1 ? -1 : 1) * sequence[i - 1];
        }
        
        long max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, dp1[i]);
            max = Math.max(max, dp2[i]);
        }
        
        return max;
    }
}