import java.util.*;

public class Main {
    private static int end = -1;
    private static int answer = -1;
    private static List<List<Integer>> graph = new ArrayList<>();
    private static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        visited = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        int start = sc.nextInt();
        end = sc.nextInt();
        
        int m = sc.nextInt();
        for (int i = 1; i <= m; i++) {
            int child = sc.nextInt();
            int parent = sc.nextInt();
            graph.get(child).add(parent);
            graph.get(parent).add(child);
        }
        
        dfs(start, 0);
        System.out.println(answer);
    }
    
    private static void dfs(int point, int cnt) {
        visited[point] = true;
        for (int x : graph.get(point)) {
            if (!visited[x]) {
                if (x == end) {
                    answer = cnt + 1;
                    return;
                }
                
                 dfs(x, cnt + 1);
            }
        }
    }
}