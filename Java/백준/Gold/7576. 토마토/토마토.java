import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] arr = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        
        int answer = new Solution().solution(m, n, arr);
        System.out.println(answer);
    }
}
class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    public int solution(int m, int n, int[][] arr) {
        return bfs(m, n, arr);
    }
    
    private int bfs(int m,
                    int n,
                    int[][] arr) {
        Queue<int[]> queue = initQueue(m, n, arr);
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 1 || nx > n || ny < 1 || ny > m) {
                    continue;
                }
                
                if (arr[nx][ny] != 0) {
                    continue;
                }
                
                arr[nx][ny] = arr[x][y] + 1;
                queue.add(new int[]{nx, ny});
            }
        }
        
        if (isRemainZero(m, n, arr)) {
            return -1;
        }
        
        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                max = Math.max(max, arr[i][j]);
            }
        }
        
        return max - 1;
    }
    
    private boolean isRemainZero(int m, int n, int[][] arr) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (arr[i][j] == 0) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private Queue<int[]> initQueue(int m, int n, int[][] arr) {
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (arr[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        
        return queue;
    }
}