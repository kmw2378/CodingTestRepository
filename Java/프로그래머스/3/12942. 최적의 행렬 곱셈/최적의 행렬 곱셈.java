class Solution {
    int[][] matrix_sizes;
    public int solution(int[][] matrix_sizes) {
        int n = matrix_sizes.length;
        int[][] dp = new int[n][n];
        
        for (int length = 1; length < n; length++) {
            for (int i = 0; i < n - length; i++) {
                int j = i + length;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + 
                               matrix_sizes[i][0] * matrix_sizes[k][1] * matrix_sizes[j][1];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        
        return dp[0][n - 1];
    }
    
    private int getMatrixMultiple(int[] matrix1, int[] matrix2) {
        return matrix1[0] * matrix1[1] * matrix2[1];
    }
}