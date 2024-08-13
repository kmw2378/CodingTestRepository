import java.util.*;

class Solution {
    int[] dx = {-1, -1, 1, 1};
    int[] dy = {-1, 1, -1, 1};
    int answer = 0;
    
    public int solution(int n) {
        backtracking(new boolean[n][n], n, 0);
        return answer;
    }
    
    private void backtracking(boolean[][] visited, int n, int row) {
        if (row == n) {
            answer++;
            return;
        }
        
        for (int i = 0; i < n; i++) {
            visited[row][i] = true;
            if (satisfied(visited, n, row, i)) {
                backtracking(visited, n, row + 1);
            }
            visited[row][i] = false;
        }
    }
    
    private boolean satisfied(boolean[][] visited, int n, int row, int column) {
        for (int i = 0; i < n && i != column; i++) {
            if (visited[row][i]) {
                return false;
            }
        }
        
        for (int i = 0; i < n && i != row; i++) {
            if (visited[i][column]) {
                return false;
            }
        }
        
        for (int i = 1; i < n; i++) {
            int nRow = row + i;
            int nColumn = column + i;
            if (nRow >= n || nColumn >= n) {
                break;
            }  
            
            if (visited[nRow][nColumn]) {
                return false;
            }
        }
        
        for (int i = 1; i < n; i++) {
            int nRow = row + i;
            int nColumn = column - i;
            if (nRow >= n || nColumn < 0) {
                break;
            }  
            
            if (visited[nRow][nColumn]) {
                return false;
            }
        }
        
        for (int i = 1; i < n; i++) {
            int nRow = row - i;
            int nColumn = column + i;
            if (nRow < 0 || nColumn >= n) {
                break;
            }  
            
            if (visited[nRow][nColumn]) {
                return false;
            }
        }
        
        for (int i = 1; i < n; i++) {
            int nRow = row - i;
            int nColumn = column - i;
            if (nRow < 0 || nColumn < 0) {
                break;
            }
            
            if (visited[nRow][nColumn]) {
                return false;
            }
        }
        
        return true;
    }
}