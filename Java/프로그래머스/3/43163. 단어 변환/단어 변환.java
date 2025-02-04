class Solution {
    int answer = Integer.MAX_VALUE;
    
    public int solution(String begin, String target, String[] words) {
        backtracking(begin, 0, words, target, new boolean[words.length]);
        if (answer == Integer.MAX_VALUE) {
            return 0;
        }
        return answer;
    }
    
    private void backtracking(String currentWord, int cnt, String[] words, String target, boolean[] visited) {
        if (currentWord.equals(target)) {
            answer = Math.min(cnt, answer);
            return;
        }
        
        for (int i = 0; i < words.length; i++) {
            String nextWord = words[i];
            if (visited[i] || !canChange(currentWord, nextWord)) {
                continue;
            }
            
            visited[i] = true;
            backtracking(nextWord, cnt + 1, words, target, visited);
            visited[i] = false;
        }
    }
    
    private boolean canChange(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        
        int length = a.length();
        int differentCnt = 0;
        for (int i = 0; i < length; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                differentCnt++;
            }
            
            if (differentCnt > 1) {
                return false;
            }
        }
        
        return differentCnt == 1;
    }
}