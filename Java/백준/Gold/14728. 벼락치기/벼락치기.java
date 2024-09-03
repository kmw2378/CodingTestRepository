import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int T = sc.nextInt();
        int[][] arr = new int[N + 1][3];
        for (int i = 1; i <= N; i++) {
            arr[i][1] = sc.nextInt();
            arr[i][2] = sc.nextInt();
        }
        
        int answer = new Solution().solution(N, T, arr);
        System.out.println(answer);
    }
}
class Solution {
    public int solution(int N, int T, int[][] arr) {
        // T : 총 비용
        // arr[n][1] : 비용
        // arr[n][2] : 이익
        
        int[][] dp = new int[N + 1][T + 1];
        // dp[n][t] : 1 ~ n번 과목 공부, 비용 t일 때 최대 이익
        
        for (int n = 1; n <= N; n++) {
            for (int t = 0; t <= T; t++) {
                dp[n][t] = dp[n - 1][t];
                if (t - arr[n][1] >= 0) {
                    dp[n][t] = Math.max(dp[n][t], dp[n - 1][t - arr[n][1]] + arr[n][2]);
                }
            }
        }
        
        int max = 0;
        for (int t = 0; t <= T; t++) {
            max = Math.max(dp[N][t], max);
        }
        
        return max;
    } 
}