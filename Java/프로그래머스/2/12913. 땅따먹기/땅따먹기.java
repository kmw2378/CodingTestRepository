class Solution {
    int solution(int[][] land) {
        int r = land.length;
        int c = land[0].length;
        int[][] dp = new int[r + 1][c + 1];
        // dp[i][j]: i번째 시도에서 j번째 땅을 밟았을 때 최대
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                for (int k = 1; k <= c; k++) {
                    if (j == k) {
                        continue;
                    }
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + land[i - 1][j - 1]);
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= c; i++) {
            answer = Math.max(answer, dp[r][i]);
        }
        return answer;
    }
}