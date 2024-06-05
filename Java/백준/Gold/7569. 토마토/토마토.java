import java.util.*;

class Main {
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int h = sc.nextInt();
        int[][][] arr = new int[h + 1][n + 1][m + 1];
        
        Queue<int[]> queue = new LinkedList<>();
        int notRipeCnt = 0;
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= m; k++) {
                    arr[i][j][k] = sc.nextInt();
                    if (arr[i][j][k] == 1) {
                        queue.add(new int[]{i, j, k});
                    } else if (arr[i][j][k] == 0) {
                        notRipeCnt++;
                    }
                }
            }
        }
        
        int day = 0;
        while (notRipeCnt > 0 && !queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                for (int j = 0; j < 6; j++) {
                    int nextZ = current[0] + dz[j];
                    int nextY = current[1] + dy[j];
                    int nextX = current[2] + dx[j];
                    if (nextX <= 0 || nextX > m || nextY <= 0 || nextY > n || nextZ <= 0 || nextZ > h) {
                        continue;
                    }
                
                    if (arr[nextZ][nextY][nextX] != 0) {
                        continue;
                    }
                    
                    notRipeCnt--;
                    arr[nextZ][nextY][nextX] = 1;
                    queue.add(new int[]{nextZ, nextY, nextX});
                }
            }
            day++;
        }
        
        if (notRipeCnt != 0) {
            day = -1;
        }
        
        System.out.println(day);
    }
}