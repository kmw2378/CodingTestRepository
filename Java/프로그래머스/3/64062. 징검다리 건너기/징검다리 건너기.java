import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int answer = Integer.MIN_VALUE;     
        int left = 0;
        int right = 200_000_000;
        while (left <= right) {
            int mid = (left + right) / 2;   // 건널 수 있는 친구의 수
            if (canAccross(stones, k, mid)) {
                answer = Math.max(answer, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return answer;
    }
    
    private boolean canAccross(int[] stones, int k, int count) {
        int skip = 0;   // 못 건너는 징검다리 개수 저장
        for (int stone : stones) {
            if (stone < count) {
                skip++;
            } else {
                skip = 0;
            }
            
            if (skip == k) {
                return false;
            }
        }
        
        return true;
    }
}