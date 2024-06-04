import java.util.*;

class Solution {
    int[] costs;
    Map<Integer, List<Integer>> map = new HashMap<>();
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        costs = new int[n + 1];
        Arrays.fill(costs, -1);
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] road : roads) {
            map.get(road[0]).add(road[1]);
            map.get(road[1]).add(road[0]);
        }

        dijkstra(destination);
        int[] answer = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            answer[i] = costs[sources[i]];
        }
        
        return answer;
    }
    
    private void dijkstra(int start) {
        Queue<Integer> queue = new LinkedList<>();
        costs[start] = 0;
        queue.add(start);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next : map.get(current)) {
                if (costs[next] == -1) {
                    costs[next] = costs[current] + 1;
                    queue.add(next);
                }
            }
        }
    }
}