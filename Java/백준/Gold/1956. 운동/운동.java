import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        int[][] costs = new int[v + 1][v + 1];
        for (int[] cost : costs) {
            Arrays.fill(cost, 1_000_000_000);
        }
        
        for (int i = 1; i <= e; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            costs[from][to] = sc.nextInt();
        }
        for (int i = 1; i <= v; i++) {
            costs[i][i] = 0;
        }
        
        int answer = new Solution().solution(v, costs);
        System.out.println(answer);
    }
    
}
class Solution {
    public int solution(int v, int[][] costs) {
        floyd(costs, v);
        
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                if (i == j) {
                    continue;
                }
                
                // 자기 자신을 제외한 두 정점이 서로에게 가는 경로가 있으면 사이클 존재
                if (costs[i][j] != 1_000_000_000 && costs[j][i] != 1_000_000_000) {
                    answer = Math.min(answer, costs[i][j] + costs[j][i]);
                }
            }
        }
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    private void floyd(int[][] costs, int v) {
        for (int mid = 1; mid <= v; mid++) {
            for (int from = 1; from <= v; from++) {
                for (int to = 1; to <= v; to++) {
                    costs[from][to] = Math.min(
                        costs[from][to],
                        costs[from][mid] + costs[mid][to]
                    );
                }
            }
        }
    }
}