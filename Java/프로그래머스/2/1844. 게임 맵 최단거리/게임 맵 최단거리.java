import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        int[] start = {0, 0};
        return dijkstra(maps, start, n, m);
    }
    
    private int dijkstra(int[][] maps, int[] start, int n, int m) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] costs = new int[n][m];
        for (int[] cost : costs) {
            Arrays.fill(cost, Integer.MAX_VALUE);
        }
        
        queue.add(start);
        costs[start[0]][start[1]] = 1;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m || maps[nx][ny] == 0) {
                    continue;
                }
                
                if (costs[nx][ny] > costs[x][y] + 1) {
                    costs[nx][ny] = costs[x][y] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        
        return costs[n - 1][m - 1] == Integer.MAX_VALUE ? -1 : costs[n - 1][m - 1];
    }
}