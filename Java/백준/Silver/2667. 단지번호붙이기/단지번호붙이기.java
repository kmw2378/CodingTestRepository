import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            board[i] = Arrays.stream(sc.nextLine().split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        }
        
        new Solution().solution(n, board);
    }
}
class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    boolean[][] visited;
    
    public void solution(int n, int[][] board) {
        int cnt = 0;
        Queue<Integer> queue = new PriorityQueue<>();
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] || board[i][j] == 0) {
                    continue;
                }
                
                cnt++;
                queue.add(bfs(new int[]{i, j}, n, board));
            }
        }
        
        System.out.println(cnt);
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
    
    private int bfs(int[] start, int n, int[][] board) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        visited[start[0]][start[1]] = true;
        int cnt = 1;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                    continue;
                }
                
                if (visited[nx][ny] || board[nx][ny] == 0) {
                    continue;
                }
                
                cnt++;
                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny});
            }
        }
        
        return cnt;
    }
}