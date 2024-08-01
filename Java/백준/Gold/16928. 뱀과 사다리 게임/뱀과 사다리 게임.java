import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Map<Integer, Integer> ladder = new HashMap<>();
        for (int i = 1; i <= n + m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            ladder.put(from, to);
        }
        
        int answer = new Solution().solution(ladder);
        System.out.println(answer);
    }
}
class Solution {    
    public int solution(Map<Integer, Integer> ladder) {
        return bfs(1, 100, ladder);
    }
    
    private int bfs(int start, int end, Map<Integer, Integer> ladder) {
        Queue<Integer> queue = new LinkedList<>();
        int[] costs = new int[101];
        Arrays.fill(costs, 0);
        queue.add(start);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int i = 1; i <= 6; i++) {
                int next = ladder.getOrDefault(current + i, current + i);
                if (next > end) {
                    break;
                }
                
                if (costs[next] != 0) {
                    continue;
                }
                
                costs[next] = costs[current] + 1;
                queue.add(next);
            }
        }
        
        return costs[end];
    }
}