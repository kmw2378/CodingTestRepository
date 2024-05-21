import java.util.*;

public class Main {
    static int answer = 0;
    static Map<Integer, List<Integer>> map;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        visited = new boolean[n + 1];
        map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }
        
        int m = sc.nextInt();
        for (int i = 1; i <= m; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            map.get(start).add(end);
            map.get(end).add(start);
        }
        
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfs(i);
                answer++;
            }
        }
        System.out.println(answer);
    }
    
    private static void dfs(int p) {
        visited[p] = true;
        boolean flag = true;
        for (int x : map.get(p)) {
            if (!visited[x]) {
                dfs(x);
            }
        }
    }
}