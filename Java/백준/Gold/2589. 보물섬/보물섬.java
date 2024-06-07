import java.util.*;

class Main {
    static int r;
    static int c;
    static char[][] arr;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        arr = new char[r + 1][c + 1];
        sc.nextLine();
        for (int i = 1; i <= r; i++) {
            String s = sc.nextLine();
            for (int j = 1; j <= c; j++) {
                arr[i][j] = s.charAt(j - 1);
            }
        }
        
        int answer = 0;
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (arr[i][j] == 'L') {
                    answer = Math.max(answer, dijkstra(i, j));
                }
            }
        }
        
        System.out.println(answer);
    }
    private static int dijkstra(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] costs = new int[r + 1][c + 1];
        for (int[] cost : costs) {
            Arrays.fill(cost, -1);
        }
        
        costs[x][y] = 0;
        queue.add(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];
                if (nx <= 0 || ny <= 0 || nx > r || ny > c) {
                    continue;
                }
            
                if (arr[nx][ny] == 'L' && costs[nx][ny] == -1) {
                    costs[nx][ny] = costs[current[0]][current[1]] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        
        int max = 0;
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
               max = Math.max(costs[i][j], max); 
            }
        }
        return max;
    }
}