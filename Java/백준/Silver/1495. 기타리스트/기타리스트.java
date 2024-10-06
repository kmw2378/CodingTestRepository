import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int m = sc.nextInt();
        
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = sc.nextInt();
        }
        
        int answer = new Solution().solution(n, s, m, v);
        System.out.println(answer);
    }
}

class Solution {
    public int solution(int n, int s, int m, int[] v) {
        // dp[i][p] : i번째 곡을 연주하기 전에 볼륨 p로 설정 가능한지?
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][s] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (dp[i - 1][j]) {
                    if (j + v[i - 1] <= m) {
                        dp[i][j + v[i - 1]] = true;
                    }
                    
                    if (j - v[i - 1] >= 0) {
                        dp[i][j - v[i - 1]] = true;
                    }
                }
            }
        }
        
        for (int i = m; i >= 0; i--) {
            if (dp[n][i]) {
                return i;
            }
        }
        
        return -1;
    }
}