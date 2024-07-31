import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Map<Integer, List<Integer>> edges = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            edges.put(i, new ArrayList<>());
        }
        
        for (int i = 1; i <= n - 1; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            edges.get(from).add(to);
            edges.get(to).add(from);
        }
        
        new Solution().solution(n, edges);
    }
}
class Solution {
    public void solution(int n, Map<Integer, List<Integer>> edges) {
        int[] answer = new int[n + 1];
        dfs(1, new boolean[n + 1], edges, answer);
        for (int i = 2; i <= n; i++) {
            System.out.println(answer[i]);
        }
    }
    
    private void dfs(int current, 
                     boolean[] visited, 
                     Map<Integer, List<Integer>> edges,
                     int[] answer) {
        visited[current] = true;
        for (int x : edges.get(current)) {
            if (visited[x]) {
                continue;
            }
            answer[x] = current;
            dfs(x, visited, edges, answer);
        }
    }
}