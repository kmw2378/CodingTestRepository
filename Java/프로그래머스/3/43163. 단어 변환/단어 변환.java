class Solution {
    boolean[] visited;
    int answer = Integer.MAX_VALUE;
    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
        dfs(begin, target, words, 0);
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
    
    private void dfs(String current, String target, String[] words, int cnt) {
        if (current.equals(target)) {
            answer = Math.min(answer, cnt);
            return;
        }
        
        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && isDifferentOneChar(current, words[i])) {
                visited[i] = true;
                dfs(words[i], target, words, cnt + 1);
                visited[i] = false;
            }
        }
    }
    
    private boolean isDifferentOneChar(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        
        int cnt = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                cnt++;
            }
        }
        
        return cnt == 1;
    }
}