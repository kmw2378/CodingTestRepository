import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }
        
        new Solution().solution(n, arr);
    }
}
class Solution {
    int[] arr;
    boolean[] visited;
    List<Integer> list = new ArrayList<>();
    
    public void solution(int n, int[] arr) {
        visited = new boolean[n + 1];
        this.arr = arr;
        
        for (int i = 1; i <= n; i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }
        
        System.out.println(list.size());
        Collections.sort(list);
        list.forEach(System.out::println);
    }
    
    private void dfs(int start, int target) {
        if (!visited[arr[start]]) {
            visited[arr[start]] = true;
            dfs(arr[start], target);
            visited[arr[start]] = false;
        }
        
        // 사이클이 발생한 경우
        if (arr[start] == target) {
            list.add(target);
        }
    }
}