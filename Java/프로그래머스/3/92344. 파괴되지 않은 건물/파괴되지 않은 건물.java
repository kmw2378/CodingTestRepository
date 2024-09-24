class Solution {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        int[][] impact = new int[n + 1][m + 1];
        
        for (int[] s : skill) {
            int type = s[0];
            int r1 = s[1], c1 = s[2], r2 = s[3], c2 = s[4];
            int degree = (type == 1 ? -1 : 1) * s[5];
            
            impact[r1][c1] += degree;
            impact[r1][c2 + 1] -= degree;
            impact[r2 + 1][c1] -= degree;
            impact[r2 + 1][c2 + 1] += degree;
        }
        
        // 가로 방향으로 누적합 계산
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                impact[i][j] += impact[i][j - 1];
            }
        }
        
        // 세로 방향으로 누적합 계산
        for (int j = 0; j < m; j++) {
            for (int i = 1; i < n; i++) {
                impact[i][j] += impact[i - 1][j];
            }
        }
        
        // 최종 생존 카운트 계산
        int surviveCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] += impact[i][j];
                if (board[i][j] > 0) surviveCount++;
            }
        }
        
        return surviveCount;
    }
}
