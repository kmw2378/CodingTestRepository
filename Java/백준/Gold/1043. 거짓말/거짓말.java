import java.util.*;

class Main {
    static boolean[] visited;
    static Map<Integer, List<Integer>> map = new HashMap<>();
    static int[] parent;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        int size = sc.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }
        
        visited = new boolean[n + 1];
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        for (int i = 1; i <= m; i++) {
            map.put(i, new ArrayList<>());
        }
        
        for (int i = 1; i <= m; i++) {
            int cnt = sc.nextInt();
            for (int j = 1; j <= cnt; j++) {
                int x = sc.nextInt();
                map.get(i).add(x);
            }
        }
        
        for (int i = 1; i <= m; i++) {
            int first = map.get(i).get(0);
            for (int j = 1; j < map.get(i).size(); j++) {
                union(first, map.get(i).get(j));
            }
        }
        
        int cnt = 0;
        for (int i = 1; i <= m; i++) {
            int target = map.get(i).get(0);
            boolean know = Arrays.stream(arr)
                .anyMatch(e -> isUnion(target, e));
            if (!know) {
                cnt++;
            }
        }
        
        System.out.println(cnt);
    }
    
    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }
    
    private static int find(int a) {
       if (parent[a] == a) {
           return a;
       }   
       
       parent[a] = find(parent[a]);
       return parent[a];
    }
    
    private static boolean isUnion(int a, int b) {
        return find(a) == find(b);
    }
}