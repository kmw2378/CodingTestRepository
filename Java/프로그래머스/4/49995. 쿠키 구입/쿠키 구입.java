import java.util.*;

class Solution {
    public int solution(int[] cookie) {
        int answer = 0;
        int n = cookie.length;
        
        int[] sums = new int[n + 1];    // sums[i]: 1 ~ (i + 1) 까지 합
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + cookie[i];
        }

        for (int mid = 0; mid < n - 1; mid++) {
            int left = mid;
            int right = mid + 1;
            while (true) {
                int leftSum = sums[mid + 1] - sums[left];   // left ~ mid 까지 합
                int rightSum = sums[right + 1] - sums[mid + 1];     // (mid + 1) ~ right 까지 합
                
                if (leftSum == rightSum) {
                    answer = Math.max(answer, leftSum);
                }
                
                if (leftSum >= rightSum && right < n - 1) {
                    right++;
                } else if (leftSum <= rightSum && left > 0) {
                    left--;
                } else {
                    break;
                }
            }
        }
        
        return answer;
    }
}