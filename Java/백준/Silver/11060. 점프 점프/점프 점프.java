import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        int answer = new Solution().solution(arr);
        System.out.println(answer);
    }
}

class Solution {
    public int solution(int[] arr) {
        int[] dp = new int[arr.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < arr.length; i++) {
            if (dp[i] == Integer.MAX_VALUE) {
                continue;
            }
            
            for (int j = 1; j <= arr[i]; j++) {
                if (i + j < arr.length) {
                    dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
                }
            }
        }
        
        return dp[arr.length - 1] == Integer.MAX_VALUE ? -1 : dp[arr.length - 1];
    }
}