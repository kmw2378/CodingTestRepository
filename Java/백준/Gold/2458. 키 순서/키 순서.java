import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] costs = new int[n + 1][n + 1];
        for (int[] cost : costs) {
            Arrays.fill(cost, 1_000_000_000);
        }
        
        for (int i = 0; i <= n; i++) {
            costs[i][i] = 0;
        }
        
        for (int i = 1; i <= m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            costs[from][to] = 1;
        }
        
        int answer = new Solution().solution(costs, n);
        System.out.println(answer);
    }
}
class Solution {
    public int solution(int[][] costs, int n) {
        int answer = 0;
        floyd(costs, n);
        for (int i = 1; i <= n; i++) {
            boolean canCheck = true;
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    continue;
                }

                if (costs[i][j] == 1_000_000_000 && costs[j][i] == 1_000_000_000) {
                    canCheck = false;
                    break;
                }
            }
            
            if (canCheck) {
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