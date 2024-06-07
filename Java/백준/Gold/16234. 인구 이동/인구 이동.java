import java.util.*;

class Main {
    static int n;
    static int l;
    static int r;
    static int[][] arr;
    static boolean[][] visit;
    static List<int[]> list;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        l = sc.nextInt();
        r = sc.nextInt();
        
        arr = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        
        int day = 0;
        while (true) {
            visit = new boolean[n + 1][n + 1];
            boolean isMoved = false;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (!visit[i][j]) {
                        int sum = bfs(i, j);
                        if (list.size() > 1) {
                            move(sum);
                            isMoved = true;
                        }
                    }
                }
            }
            
            if (!isMoved) {
                break;
            }
            day++;
        }    
        System.out.println(day);
    }
    
    private static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        list = new ArrayList<>();
        
        int[] start = new int[]{x, y};
        queue.add(start);
        list.add(start);
        visit[x][y] = true;
        int sum = arr[x][y];
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = current[0] + dx[i];
                int nextY = current[1] + dy[i];
                if (nextX <= 0 || nextX > n || nextY <= 0 || nextY > n) {
                    continue;
                }
                if (!visit[nextX][nextY]) {
                    int diff = Math.abs(arr[current[0]][current[1]] - arr[nextX][nextY]);
                    if (l <= diff && diff <= r) {
                        int[] next = new int[]{nextX, nextY};
                        visit[nextX][nextY] = true;
                        queue.add(next);
                        list.add(next);
                        sum += arr[nextX][nextY];
                    }     
                }
            }
        }
        
        return sum;
    }
    
    private static void move(int sum) {
        int avg = sum / list.size();
        for (int[] point : list) {
            arr[point[0]][point[1]] = avg;
        }
    }
}