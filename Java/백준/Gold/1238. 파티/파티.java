import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int x = sc.nextInt();
        int[][] costs = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    continue;
                }
                
                costs[i][j] = 1_000_000_000;
            }
        }
        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            costs[from][to] = sc.nextInt();
        }
        
        int answer = new Solution().solution(costs, x, n);
        System.out.println(answer);
    }
}
class Solution {
    public int solution(int[][] costs,
                        int x,
                        int n) {
        floyd(costs, n);
        return getMaxCost(costs, x, n);
    }
    
    private void floyd(int[][] costs, int n) {
        // 경유지
        for (int mid = 1; mid <= n; mid++) {
            // 출발
            for (int from = 1; from <= n; from++) {
                // 도착
                for (int to = 1; to <= n; to++) {
                    costs[from][to] = Math.min(costs[from][to], costs[from][mid] + costs[mid][to]);
                }
            }
        }
    }
    
    private int getMaxCost(int[][] costs, int x, int n) {
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, costs[i][x] + costs[x][i]);
        }
        
        return max;
    }
}