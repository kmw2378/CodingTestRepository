import java.util.*;

class Main {
    static int n;
    static boolean[][] visited;
    static char[][] arr;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, 1, -1, 0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        sc.nextLine();
        arr = new char[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = s.charAt(j); 
            }
        }
        
        int normalCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    dfs(i, j);
                    normalCnt++;
                }
            }
        }
        
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 'R') {
                    arr[i][j] = 'G';
                }
            }
        }
        
        int abnormalCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    dfs(i, j);
                    abnormalCnt++;
                }
            }
        }
        
        System.out.println(normalCnt + " " + abnormalCnt);
    }
    
    private static void dfs(int x, int y) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            
            if (newX < 0 || newY < 0 || newX >= n || newY >= n) {
                continue;
            }
            
            if (!visited[newX][newY] && arr[newX][newY] == arr[x][y]) {
                dfs(newX, newY);
            }
        }
    }
}