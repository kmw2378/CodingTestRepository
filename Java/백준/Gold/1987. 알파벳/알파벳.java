import java.util.*;

class Main {
    static int r;
    static int c;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static char[][] arr;
    static boolean[][] visited;
    static int result = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        sc.nextLine();
        arr = new char[r + 1][c + 1];
        visited = new boolean[r + 1][c + 1];
        for (int i = 1; i <= r; i++) {
            String s = sc.nextLine();
            for (int j = 1; j <= c; j++) {
                arr[i][j] = s.charAt(j - 1);
            }
        }
        
        boolean[] alphabet = new boolean[26];
        alphabet[(int)arr[1][1] - (int)'A'] = true;
        dfs(1, 1, alphabet, 1);
        System.out.println(result);
    }
    
    private static void dfs(int x, int y, boolean[] alphabet, int count) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx <= 0 || ny <= 0 || nx > r || ny > c) {
                continue;
            }
            
            int idx = (int)arr[nx][ny] - (int)'A';
            if (!visited[nx][ny] && !alphabet[idx]) {
                boolean[] newAlphabet = alphabet.clone();
                newAlphabet[idx] = true;
                dfs(nx, ny, newAlphabet, count + 1);
            }
        }
        visited[x][y] = false;
        result = Math.max(count, result);
    } 
}