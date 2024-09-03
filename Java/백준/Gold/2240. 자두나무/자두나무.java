import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int w = sc.nextInt();
        int[] arr = new int[t + 1];
        for (int i = 1; i <= t; i++) {
            arr[i] = sc.nextInt();
        }
        
        int answer = new Solution().solution(t, w, arr);
        System.out.println(answer);
    }
}
class Solution {
    public int solution(int T, int W, int[] arr) {
        int P = 2;
        int[][][] dp = new int[T + 1][W + 1][P + 1];
        dp[1][0][1] = arr[1] == 1 ? 1 : 0;
        dp[1][1][2] = arr[1] == 2 ? 1 : 0;
       
        for (int t = 2; t <= T; t++) {
            for (int w = 0; w <= W; w++) {
                dp[t][w][1] = dp[t - 1][w][1];
                if (w > 0) {
                    dp[t][w][1] = Math.max(dp[t][w][1], dp[t - 1][w - 1][2]);
                }
                
                if (arr[t] == 1) {
                    dp[t][w][1]++;
                }
                
                dp[t][w][2] = dp[t - 1][w][2];
                if (w > 0) {
                    dp[t][w][2] = Math.max(dp[t][w][2], dp[t - 1][w - 1][1]);
                }
                
                if (arr[t] == 2) {
                    dp[t][w][2]++;
                }
            }
        }
        
        int max = 0;
        for (int t = 1; t <= T; t++) {
            for (int w = 0; w <= W; w++) {
                max = Math.max(dp[t][w][1], max);
                max = Math.max(dp[t][w][2], max);
            }
        }
        
        return max;
    }
}