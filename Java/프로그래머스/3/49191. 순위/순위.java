import java.util.*;

class Solution {
    private static final int INF = 1_000_000_000;
    
    public int solution(int n, int[][] results) {
        int[][] costs = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    continue;
                }
                
                costs[i][j] = INF;
            }
        }
        
        for (int[] result : results) {
            int from = result[0];
            int to = result[1];
            costs[from][to] = 1;
        }
        
        floyd(costs, n);
        
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            boolean aware = true;
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    continue;
                }
                
                if (costs[i][j] == INF && costs[j][i] == INF) {
                    aware = false;
                    break;
                }
            }
            
            if (aware) {
                answer++;
            }
        }
        return answer;
    }
    
    private void floyd(int[][] costs, int n) {
        for (int mid = 1; mid <= n; mid++) {
            for (int from = 1; from <= n; from++) {
                for (int to = 1; to <= n; to++) {
                    costs[from][to] = Math.min(costs[from][to], costs[from][mid] + costs[mid][to]);
                }
            }
        }
    }
}