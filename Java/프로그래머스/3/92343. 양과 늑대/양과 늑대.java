import java.util.*;

class Solution {
    int answer = 0;
    Map<Integer, List<Integer>> map = new HashMap<>();
    public int solution(int[] info, int[][] edges) {
        for (int i = 0; i < info.length; i++) {
            map.put(i, new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            map.get(from).add(to);  // 단방향   
        }
        
        dfs(0, new boolean[info.length], info, 0, 0);
        return answer;
    }
    
    private void dfs(int x, boolean[] visited, int[] info, int sheepCnt, int wolfCnt) {        
        if (info[x] == 0) {
            sheepCnt++;
        } else {
            wolfCnt++;
        }
        
        if (sheepCnt <= wolfCnt) {
            return;
        }
        
        answer = Math.max(answer, sheepCnt);
        visited[x] = true;
        for (int key : map.keySet()) {
            for (int value : map.get(key)) {
                if (visited[key] && !visited[value]) {
                    boolean[] newVisited = visited.clone();
                    dfs(value, newVisited, info, sheepCnt, wolfCnt);             
                }
            }
        }
    }
}