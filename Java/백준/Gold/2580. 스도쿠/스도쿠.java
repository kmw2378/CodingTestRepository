import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 9;
        int[][] boards = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boards[i][j] = sc.nextInt();
            }
        }
        
        int[][] answer = new Solution().solution(boards, n);
        for (int[] arr : answer) {
            for (int e : arr) {
              System.out.printf("%d ", e);
            }
            System.out.println();
         }
    }
}
class Solution {
    int[][] answer;
    public int[][] solution(int[][] boards, int n) {
        backtracking(boards, n, 0, 0);
        return answer;
    }
    
    private void backtracking(int[][] boards, int n, int r, int c) {
        if (c == n) {
            backtracking(boards, n, r + 1, 0);
            return;
        }
        
        if (r == n) {
            copyToAnswer(boards, n);
            return;
        }
        
        if (boards[r][c] == 0) {
            for (int i = 1; i <= n; i++) {
                if (satisfied(boards, n, r, c, i)) {
                    boards[r][c] = i;
                    backtracking(boards, n, r, c + 1);
                }
            }
            boards[r][c] = 0;
            return;
        }
        
        backtracking(boards, n, r, c + 1);
    }
    
    private boolean satisfied(int[][] boards, int n, int r, int c, int current) {
        for (int i = 0; i < n; i++) {
            if (boards[r][i] == current || boards[i][c] == current) {
                return false;
            }
        }
        
        int rStart = (r / 3) * 3;
        int cStart = (c / 3) * 3;
        for (int i = rStart; i < rStart + n / 3; i++) {
            for (int j = cStart; j < cStart + n / 3; j++) {
                if (boards[i][j] == current) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private void copyToAnswer(int[][] boards, int n) {
        answer = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer[i][j] = boards[i][j];
            }
        }
    }
}