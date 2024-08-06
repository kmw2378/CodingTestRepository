import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] area = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                area[i][j] = sc.nextInt();
            }
        }
        
        int answer = new Solution().solution(n, m, area);
        System.out.println(answer);
    }
}
class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int answer = 0;
    
    public int solution(int n, int m, int[][] area) {
        addWall(0, n, m, area);
        return answer;
    }
    
    private void addWall(int cnt, 
                         int n, 
                         int m,
                         int[][] area) {
        if (cnt == 3) {
            answer = Math.max(answer, getSafeAreaCnt(n, m, area));
            return;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (area[i][j] == 0) {
                    area[i][j] = 1;
                    addWall(cnt + 1, n, m, area);
                    area[i][j] = 0;
                }
            }
        }
    }
    
    private int getSafeAreaCnt(int n, int m, int[][] area) {
        int[][] copyArea = new int[n + 1][m + 1];
        for (int i = 0; i < area.length; i++) {
            copyArea[i] = area[i].clone();
        }
        
        Queue<int[]> queue = initQueue(n, m, copyArea);
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
                
                if (copyArea[nx][ny] != 0) {
                    continue;
                }
                
                copyArea[nx][ny] = 2;
                queue.add(new int[]{nx, ny});
            }
        }
        
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (copyArea[i][j] == 0) {
                    cnt++;
                }
            }
        }
        
        return cnt;
    }
    
    private Queue<int[]> initQueue(int n, int m, int[][] area) {
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (area[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        
        return queue;
    }
}