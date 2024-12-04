import java.util.*;

class Solution {
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    
    public int[] solution(String[] maps) {
        int[][] arr = new int[maps.length][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new int[maps[i].length()];
            for (int j = 0; j < maps[i].length(); j++) {
                if (maps[i].charAt(j) == 'X') {
                    arr[i][j] = -1;
                    continue;
                }
                
                arr[i][j] = (int) (maps[i].charAt(j) - '0');
            }
        }
        
        boolean[][] visited = new boolean[arr.length][arr[0].length];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (!visited[i][j] && arr[i][j] != -1) {
                    int sum = bfs(new int[]{i, j}, visited, arr);
                    if (sum != 0) {
                        list.add(sum);
                    }
                }
            }
        }
        
        if (list.isEmpty()) {
            return new int[]{-1};
        }
        
        return list.stream()
            .sorted()
            .mapToInt(e -> e)
            .toArray();
    }
    
    private int bfs(int[] start, boolean[][] visited, int[][] arr) {
        int sum = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        visited[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            sum += arr[x][y];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= arr.length || ny >= arr[0].length || arr[nx][ny] == -1 || visited[nx][ny]) {
                    continue;
                }
                
                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny});
            }
        }
        
        return sum;
    }
}