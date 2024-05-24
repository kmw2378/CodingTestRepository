class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long left = 0;
        long right = 1_000_000_000_000_000L;
        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;
            
            for (int time : times) {
                count += (mid / time);
            }
            
            if (count < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = mid;
            }
        }
        
        return answer;
    }
}