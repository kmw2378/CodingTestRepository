import java.util.*;

class Solution {
    public int solution(String dirs) {
        int answer = 0;
        Map<Character, int[]> map = new HashMap<>();
        map.put('U', new int[]{0, 1});
        map.put('D', new int[]{0, -1});
        map.put('L', new int[]{-1, 0});
        map.put('R', new int[]{1, 0});
        boolean[][][][] visited = new boolean[11][11][11][11];
        int[] current = {5, 5};
        
        for (char c : dirs.toCharArray()) {
            int[] points = map.get(c);
            if (current[0] + points[0] < 0 || current[0] + points[0] > 10 || current[1] + points[1] < 0 || current[1] + points[1] > 10) {
                continue;
            }
            
            if (!visited[current[0]][current[1]][current[0] + points[0]][current[1] + points[1]] && !visited[current[0] + points[0]][current[1] + points[1]][current[0]][current[1]]) {
                answer++;
            }
            visited[current[0]][current[1]][current[0] + points[0]][current[1] + points[1]] = true;
            visited[current[0] + points[0]][current[1] + points[1]][current[0]][current[1]] = true;
            
            current[0] += points[0];
            current[1] += points[1];
        }
        return answer;
    }
}