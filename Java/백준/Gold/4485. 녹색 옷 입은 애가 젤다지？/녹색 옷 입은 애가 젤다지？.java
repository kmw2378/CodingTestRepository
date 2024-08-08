import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int round = 1;
        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            
            int[][] costs = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    costs[i][j] = sc.nextInt();
                }
            }
            
            int answer = new Solution().solution(n, costs);
            System.out.printf("Problem %d: %d\n", round++, answer);
        }
    }
}

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    public int solution(int n, int[][] costs) {
        return dijkstra(n, new int[]{1, 1}, costs);
    }
    
    private int dijkstra(int n,
                         int[] start,
                         int[][] costs) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        int[][] totalCosts = new int[n + 1][n + 1];
        for (int[] totalCost : totalCosts) {
            Arrays.fill(totalCost, Integer.MAX_VALUE);
        }
        
        totalCosts[start[0]][start[1]] = costs[start[0]][start[1]];
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 1 || ny < 1 || nx > n || ny > n) {
                    continue;
                }
                
                if (totalCosts[nx][ny] > totalCosts[x][y] + costs[nx][ny]) {
                    totalCosts[nx][ny] = totalCosts[x][y] + costs[nx][ny];
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        
        return totalCosts[n][n];
    }
}