class Solution {
    
    public int[] solution(int target) {
        // dp[i][0]: 목표 점수 i의 던진 다트 최소
        // dp[i][1]: 목표 점수 i의 "싱글" 또는 "불" 최대 횟수 
        int[][] dp = new int[target + 1][2];
        for (int i = 1; i <= target; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }
        
        for (int i = 1; i <= target; i++) {
            for (int j = 1; j <= 20; j++) {
                // "불"을 맞춘 경우
                if (i - 50 >= 0) {
                    if (dp[i][0] > dp[i - 50][0] + 1) {
                        dp[i][0] = dp[i - 50][0] + 1;
                        dp[i][1] = dp[i - 50][1] + 1;
                    } else if (dp[i][0] == dp[i - 50][0] + 1) {
                        dp[i][1] = Math.max(dp[i][1], dp[i - 50][1] + 1);
                    }
                }
                
                // "싱글"을 맞춘 경우
                if (i - j >= 0) {
                    if (dp[i][0] > dp[i - j][0] + 1) {
                        dp[i][0] = dp[i - j][0] + 1;
                        dp[i][1] = dp[i - j][1] + 1;
                    } else if (dp[i][0] == dp[i - j][0] + 1) {
                        dp[i][1] = Math.max(dp[i][1], dp[i - j][1] + 1);
                    }
                }
                
                // "더블"을 맞춘 경우
                if (i - j * 2 >= 0) {
                    if (dp[i][0] > dp[i - j * 2][0] + 1) {
                        dp[i][0] = dp[i - j * 2][0] + 1;
                        dp[i][1] = dp[i - j * 2][1];
                    }
                }
                
                // "트리플"을 맞춘 경우
                if (i - j * 3 >= 0) {
                    if (dp[i][0] > dp[i - j * 3][0] + 1) {
                        dp[i][0] = dp[i - j * 3][0] + 1;
                        dp[i][1] = dp[i - j * 3][1];
                    }
                }
            }
        }
        
        return dp[target];
    }
}