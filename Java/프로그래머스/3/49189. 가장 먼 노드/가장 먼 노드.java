import java.util.*;

class Solution {
    int[] costs;
    Map<Integer, List<Integer>> map = new HashMap<>();
    public int solution(int n, int[][] edges) {
        costs = new int[n + 1];
        Arrays.fill(costs, -1);
        
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            map.get(from).add(to);
            map.get(to).add(from);
        }
        
        dijkstra(1);
        
        int max = Integer.MIN_VALUE;
        for (int cost : costs) {
            max = Math.max(max, cost);
        }
        
        int cnt = 0;
        for (int cost : costs) {
            if (max == cost) {
                cnt++;
            }
        }
        
        return cnt;
    }
    
    private void dijkstra(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        costs[start] = 0;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int x : map.get(current)) {
                if (costs[x] == -1 || costs[x] > costs[current] + 1) {
                    costs[x] = costs[current] + 1;
                    queue.add(x);
                }
            }
        }
    }
}