import java.util.*;

class Solution {
    public int solution(int n, int[][] roads, int k) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        
        int[][] distances = new int[n + 1][n + 1];
        for (int[] distance : distances) {
            Arrays.fill(distance, Integer.MAX_VALUE);
        }
        
        for (int[] road : roads) {
            int from = road[0];
            int to = road[1];
            int distance = road[2];
            graph.get(from).add(to);
            graph.get(to).add(from);
            distances[from][to] = Math.min(distances[from][to], distance);
            distances[to][from] = Math.min(distances[to][from], distance);
        }
        
        int[] arr = dijkstra(graph, distances, n, 1);
        int answer = 0;
        for (int e : arr) {
            if (e <= k){
                answer++;
            }
        }
        
        return answer;
    }
    
    private int[] dijkstra(Map<Integer, List<Integer>> graph,
                          int[][] distances,
                          int n,
                          int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        int[] costs = new int[n + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[start] = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int x : graph.get(current)) {
                if (costs[x] > costs[current] + distances[current][x]) {
                    costs[x] = costs[current] + distances[current][x];
                    queue.add(x);
                }
            }
        }
        
        return costs;
    }
}