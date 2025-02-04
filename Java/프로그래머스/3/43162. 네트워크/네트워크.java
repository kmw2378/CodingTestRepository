import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || computers[i][j] == 0) {
                    continue;
                }
                
                graph.get(i).add(j);
            }
        }
        
        int answer = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, visited, graph);
                answer++;
            }
        }
        
        return answer;
    }
    
    private void dfs(int current, boolean[] visited, Map<Integer, List<Integer>> graph) {
        visited[current] = true;
        for (int next : graph.get(current)) {
            if (!visited[next]) {
                dfs(next, visited, graph);
            }
        }
    }
}