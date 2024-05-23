import java.util.*;

class Solution {
    boolean[] visited;
    Map<Integer, List<Integer>> map;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n && j != i; j++) {
                if (computers[i][j] == 1) {
                    map.get(i).add(j);
                    map.get(j).add(i);
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i);
                answer++;
            }
        }
        
        return answer;
    }
    
    private void dfs(int p) {
        visited[p] = true;
        for (int x : map.get(p)) {
            if (!visited[x]) {
                dfs(x);
            }
        }
    }
}