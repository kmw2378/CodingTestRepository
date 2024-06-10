import java.util.*;

class Solution {
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    public int solution(int[][] maps) {
        return dijkstra(0, 0, maps);
    }
    
    private int dijkstra(int x, int y, int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int[][] costs = new int[n][m];
        for (int[] cost : costs) {
            Arrays.fill(cost, -1);
        }
        costs[x][y] = 1;
        queue.add(new int[]{x, y});
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }
                
                if (maps[nx][ny] == 1 && costs[nx][ny] == -1) {
                    costs[nx][ny] = costs[current[0]][current[1]] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        
        return costs[n - 1][m - 1];
    }
}