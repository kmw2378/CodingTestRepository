public class Solution {
    int answer = Integer.MAX_VALUE;

    public int solution(int[][] beginning, int[][] target) {
        int rows = beginning.length;
        int cols = beginning[0].length;

        // 2^rows * 2^cols의 모든 경우의 수 탐색
        for (int rowMask = 0; rowMask < (1 << rows); rowMask++) {
            for (int colMask = 0; colMask < (1 << cols); colMask++) {
                int[][] flipped = flip(beginning, rowMask, colMask);
                if (isMatch(flipped, target)) {
                    // rowMask와 colMask에서 뒤집은 횟수를 계산
                    int flipCount = Integer.bitCount(rowMask) + Integer.bitCount(colMask);
                    answer = Math.min(answer, flipCount);
                }
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private int[][] flip(int[][] matrix, int rowMask, int colMask) {
        int[][] temp = new int[matrix.length][matrix[0].length];
        
        // 배열 복사
        for (int i = 0; i < matrix.length; i++) {
            temp[i] = matrix[i].clone();
        }

        // 행 뒤집기
        for (int i = 0; i < matrix.length; i++) {
            if ((rowMask & (1 << i)) != 0) { // i번째 행을 뒤집음
                for (int j = 0; j < matrix[i].length; j++) {
                    temp[i][j] = 1 - temp[i][j];
                }
            }
        }

        // 열 뒤집기
        for (int j = 0; j < matrix[0].length; j++) {
            if ((colMask & (1 << j)) != 0) { // j번째 열을 뒤집음
                for (int i = 0; i < matrix.length; i++) {
                    temp[i][j] = 1 - temp[i][j];
                }
            }
        }
        return temp;
    }

    private boolean isMatch(int[][] matrix1, int[][] matrix2) {
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[i].length; j++) {
                if (matrix1[i][j] != matrix2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}