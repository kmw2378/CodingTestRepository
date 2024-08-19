import java.util.*;

class Solution {
    Map<Integer, List<Integer>> graph;
    int answer = Integer.MAX_VALUE;
    
    public int solution(int n, int[][] wires) {
        for (int i = 0; i < wires.length; i++) {
            graph = new HashMap<>();
            for (int j = 1; j <= n; j++) {
                graph.put(j, new ArrayList<>());
            }
            for (int j = 0; j < wires.length; j++) {
                if (i == j) {
                    continue;
                }
                int[] wire = wires[j];
                int from = wire[0];
                int to = wire[1];
                graph.get(from).add(to);
                graph.get(to).add(from);
            }
            
            int start1 = wires[i][0];
            int start2 = wires[i][1];
            answer = Math.min(
                Math.abs(bfs(start1, n) - bfs(start2, n)),
                answer
            );
        }
        
        return answer;
    }
    
    private int bfs(int start, int n) {
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        queue.add(start);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            visited[current] = true;
            for (int next : graph.get(current)) {
                if (visited[next]) {
                    continue;
                }
                
                queue.add(next);
                count++;
            }
        }
        
        return count;
    }
}