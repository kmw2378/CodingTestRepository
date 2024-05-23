import java.util.*;

class Solution {
    boolean[] visited;
    Queue<String> queue = new PriorityQueue<>();
    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        dfs("ICN", "ICN", tickets, 0);
        return queue.peek().split(" ");
    }
    
    private void dfs(String current, String path, String[][] tickets, int cnt) {
        if (cnt == tickets.length) {
            queue.add(path);
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(current)) {
                visited[i] = true;
                dfs(tickets[i][1], path + " " + tickets[i][1], tickets, cnt + 1);
                visited[i] = false;
            }
        }
    }
}