import java.util.*;

class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = 1_000_000_000_000_000L;
        int cityLength = g.length;
        long left = 0;
        long right = 1_000_000_000_000_000L;
        while (left <= right) {
            long mid = (left + right) / 2;
            long goldCnt = 0;
            long silverCnt = 0;
            long totalCnt = 0;
            for (int i = 0; i < cityLength; i++) {
                long moveCnt = mid / (t[i] * 2);
                if (mid % (t[i] * 2) >= t[i]) {
                    moveCnt++;  // 편도로 한 번 더 갈 수 있음
                }
                
                goldCnt += Math.min(g[i], w[i] * moveCnt);
                silverCnt += Math.min(s[i], w[i] * moveCnt);
                totalCnt += Math.min(g[i] + s[i], w[i] * moveCnt);
            }
            
            if (goldCnt >= a && silverCnt >= b && totalCnt >= a + b) {
                right = mid - 1;
                answer = Math.min(mid, answer);
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
}