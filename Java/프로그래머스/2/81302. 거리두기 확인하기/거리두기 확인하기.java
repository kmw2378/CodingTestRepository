import java.util.*;

class Solution {
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    boolean[][] visited;
    int[] answer;
    
    public int[] solution(String[][] places) {
        answer = new int[places.length];
        Arrays.fill(answer, 1);
        for (int i = 0; i < answer.length; i++) {
            solution(i, places[i]);
        }

        return answer;
    }
    
    public void solution(int seq, String[] place) {
        visited = new boolean[5][5];
        for (int i = 0; i < place.length; i++) {
            for (int j = 0; j < place[i].length(); j++) {
                if (!visited[i][j] && place[i].charAt(j) == 'P') {
                    visited[i][j] = true;
                    dfs(seq, i, j, 0, place);
                    visited[i][j] = false;
                }
            }
        }
    }
    
    private void dfs(int seq, int x, int y, int count, String[] place) {
        if (count > 2) {
            return;
        }
        
        if (0 < count && count <= 2 && place[x].charAt(y) == 'P') {
            answer[seq] = 0;
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) {
                continue;
            }
            
            if (place[nx].charAt(ny) == 'X') {
                continue;
            }
            
            if (visited[nx][ny]) {
                continue;
            }
            
            visited[nx][ny] = true;
            dfs(seq, nx, ny, count + 1, place);
            visited[nx][ny] = false;
        }
    }
}