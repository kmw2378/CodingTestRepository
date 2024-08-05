import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        
        int[][] costs = new int[n + 1][n + 1];
        for (int i = 1; i <= e; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();
            
            graph.get(from).add(to);
            graph.get(to).add(from);
            costs[from][to] = cost;
            costs[to][from] = cost;
        }
        
        int x = sc.nextInt();
        int y = sc.nextInt();
        
        int answer = new Solution().solution(graph, costs, n, 1, x, y, n);
        System.out.println(answer);
    }
}
class Solution {
    public int solution(Map<Integer, List<Integer>> graph, 
                        int[][] distances,
                        int n,
                        int start,
                        int x,
                        int y,
                        int end) {
        int answer1;
        try {
            answer1 = dijkstra(graph, distances, n, start, x) 
            + dijkstra(graph, distances, n, x, y)
            + dijkstra(graph, distances, n, y, n);
        } catch (Exception e) {
            answer1 = Integer.MAX_VALUE;
        }
        
        int answer2;
        try {
            answer2 = dijkstra(graph, distances, n, start, y) 
            + dijkstra(graph, distances, n, y, x)
            + dijkstra(graph, distances, n, x, n);
        } catch (Exception e) {
            answer2 = Integer.MAX_VALUE;
        }
        
        if (answer1 == Integer.MAX_VALUE && answer2 == Integer.MAX_VALUE) {
            return -1;
        }
        
        return Math.min(answer1, answer2);
    }
    
    private int dijkstra(Map<Integer, List<Integer>> graph, 
                        int[][] distances,
                        int n,
                        int start,
                        int end) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        int[] costs = new int[n + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[start] = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int e : graph.get(current)) {
                if (costs[e] > costs[current] + distances[current][e]) {
                    costs[e] = costs[current] + distances[current][e];
                    queue.add(e);
                }
            }
        }
        
        // 도달할 수 없는 경우
        if (costs[end] == Integer.MAX_VALUE) {
            throw new IllegalArgumentException();
        }
        return costs[end];
    }
}