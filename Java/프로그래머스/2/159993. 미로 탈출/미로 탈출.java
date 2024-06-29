import java.util.*;

class Solution {
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    public int solution(String[] maps) {
        char[][] board = new char[maps.length][maps[0].length()];
        int[] start = {0, 0};
        int[] stopover = {0, 0};
        int[] end = {0, 0};
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                board[i][j] = maps[i].charAt(j);
                if (board[i][j] == 'S') {
                    start[0] = i;
                    start[1] = j;
                }
                
                if (board[i][j] == 'L') {
                    stopover[0] = i;
                    stopover[1] = j;
                }
                
                if (board[i][j] == 'E') {
                    end[0] = i;
                    end[1] = j;
                }
            }
        }
        
        int result1 = dijkstra(start, stopover, board, new boolean[maps.length][maps[0].length()]);
        int result2 = dijkstra(stopover, end, board, new boolean[maps.length][maps[0].length()]);
        
        if (result1 == -1 || result2 == -1) {
            return -1;
        }
        return result1 + result2;
    }
    
    private int dijkstra(int[] start, int[] end, char[][] board, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] costs = new int[board.length][board[0].length];
        for (int[] cost : costs) {
            Arrays.fill(cost, -1);
        }
        
        int x = start[0];
        int y = start[1];
        costs[x][y] = 0;
        queue.add(start);
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];
            visited[cx][cy] = true;
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length) {
                    continue;
                }
                
                if (!visited[nx][ny] && board[nx][ny] != 'X' && costs[nx][ny] == -1) {
                    costs[nx][ny] = costs[cx][cy] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        
        return costs[end[0]][end[1]];
    }
}