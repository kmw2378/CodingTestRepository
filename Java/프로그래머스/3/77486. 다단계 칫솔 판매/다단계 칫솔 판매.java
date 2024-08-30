import java.util.*;

class Solution {
    Map<String, String> graph = new HashMap<>();
    Map<String, Integer> sell = new HashMap<>();
    Map<String, Integer> result = new HashMap<>();
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        for (String name : enroll) {
            result.put(name, 0);
        }
        
        for (int i = 0; i < enroll.length; i++) {
            graph.put(enroll[i], referral[i]);
        }
        
        for (int i = 0; i < seller.length; i++) {
            bfs(seller[i], amount[i] * 100);
        }
        
        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = result.get(enroll[i]);
        }
            
        return answer;   
    }
    
    private void bfs(String start, int amount) {
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            String current = queue.poll();
            result.put(current, result.get(current) + amount - amount / 10);
            amount /= 10;
            
            String next = graph.get(current);
            if (!next.equals("-") && amount > 0) {
                queue.add(next);
            }
        }
    }
}