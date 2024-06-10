class Solution {
    public int solution(int[][] land) {
        int answer = 0;
        int n = land.length;
        int[][] dp = new int[n][4];
        dp[0][0] = land[0][0];
        dp[0][1] = land[0][1];
        dp[0][2] = land[0][2];
        dp[0][3] = land[0][3];
        for (int i = 1; i < n; i++) {
            dp[i][0] = land[i][0] + getMax(dp[i - 1][1], dp[i - 1][2], dp[i - 1][3]);
            dp[i][1] = land[i][1] + getMax(dp[i - 1][0], dp[i - 1][2], dp[i - 1][3]);
            dp[i][2] = land[i][2] + getMax(dp[i - 1][0], dp[i - 1][1], dp[i - 1][3]);
            dp[i][3] = land[i][3] + getMax(dp[i - 1][0], dp[i - 1][1], dp[i - 1][2]);
        }

        return getMax(dp[n - 1][0], dp[n - 1][1], dp[n - 1][2], dp[n - 1][3]);
    }
    
    private int getMax(int a, int b, int c) {
        if (a > b && a > c) {
            return a;
        }
        
        return Math.max(b, c);
    }
    
    private int getMax(int a, int b, int c, int d) {
        if (a > b && a > c && a > d) {
            return a;
        }
        
        return getMax(b, c, d);
    }
}