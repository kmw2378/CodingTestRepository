class Solution {
    int answer = Integer.MAX_VALUE;
    public int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        dfs(begin, target, words, visited, 1);
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
    
    private int dfs(String begin, String target, String[] words, boolean[] visited, int count) {
        if (begin.equals(target)) {
            answer = count - 1;
        }
        
        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && diffOneChar(begin, words[i])) {
                visited[i] = true;
                answer = Math.min(answer, dfs(words[i], target, words, visited, count + 1));
                visited[i] = false;
            }
        }
        
        return answer;
    }
    
    private boolean diffOneChar(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
            }
        }
        
        return count == 1;
    }
}