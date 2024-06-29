import java.util.*;

class Solution {
    int[] dx = {-1, 0, -1};
    int[] dy = {0, -1, -1};
    int[] mx = {-1, 1, 0, 0};
    int[] my = {0, 0, -1, 1};
    boolean isEnd = false;
    boolean[][] visited;
    int answer = 0;
    public int solution(int m, int n, String[] board) {
        char[][] arr = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = board[i].charAt(j);
            }
        }
        
        while (true) {
            boolean[][] erase = new boolean[m][n];
            visited = new boolean[m][n];
            isEnd = true;
            boolean flag = false;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] != '-') {
                        bfs(m, n, i, j, arr, erase);
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }
            
            if (isEnd || !flag) {
                break;
            }
            apply(m, n, arr, erase);
        }
        
        return answer;
    }
    
    private void bfs(int m, int n, int x, int y, char[][] board, boolean[][] erase) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];
            if (visited[cx][cy] || board[cx][cy] == '-') {
                continue;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = cx + mx[i];
                int ny = cy + my[i];
                if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                    continue;
                }
                if (!visited[nx][ny]) {
                    queue.add(new int[]{nx, ny});
                }
            }
            
            if (board[cx][cy] == '-') {
                continue;
            }
            
            visited[cx][cy] = true;
            boolean erasable = true;
            for (int i = 0; i < 3; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                    erasable = false;
                    continue;
                }
                
                if (board[cx][cy] != board[nx][ny]) {
                    erasable = false;
                }
            }
            
            if (erasable) {
                isEnd = false;
                erase[cx][cy] = true;
                for (int i = 0; i < 3; i++) {
                    erase[cx + dx[i]][cy + dy[i]] = true;
                }
            }
        }
    }
    
    private void apply(int m, int n, char[][] board, boolean[][] erase) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (erase[i][j]) {
                    board[i][j] = '-';
                    answer++;
                }
            }
        }
        
        for (int j = 0; j < n; j++) {
            for (int i = m - 2; i >= 0; i--) {
                if (board[i][j] == '-') {
                    continue;
                }
                int k = i;
                while (k < m - 1 && board[k + 1][j] == '-') {
                    char temp = board[k][j];
                    board[k][j] = board[k + 1][j];
                    board[k + 1][j] = temp;
                    k++;
                }
            }
        }
    }
}