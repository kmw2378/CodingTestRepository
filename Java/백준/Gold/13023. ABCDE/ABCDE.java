import java.util.*;

public class Main {
    static boolean visited[];
    static Map<Integer, List<Integer>> map;
    static int answer = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        visited = new boolean[n];
        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        
        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            map.get(from).add(to);
            map.get(to).add(from);
        }
        
        for (int i = 0; i < n; i++) {
            if (answer != 1) {
                dfs(i, 1);
            }
        }
        
        System.out.println(answer);
    }
    
    private static void dfs(int p, int cnt) {
        if (cnt == 5) {
            answer = 1;
            return;
        }
        visited[p] = true;
        for (int x : map.get(p)) {
            if (!visited[x]) {
                dfs(x, cnt + 1);   
            }
        }
        visited[p] = false;
    }
}