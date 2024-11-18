class Solution {
    int answer = 0;
    
    public int solution(int k, int[][] dungeons) {
        // dungeons[0]: 최소 필요 피로도
        // dungeons[1]: 소모 피로도
        backtracking(k, 0, dungeons, new boolean[dungeons.length]);
        return answer;
    }
    
    private void backtracking(int remain, int cnt, int[][] dungeons, boolean[] visited) {
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && remain >= dungeons[i][0]) {
                visited[i] = true;
                backtracking(remain - dungeons[i][1], cnt + 1, dungeons, visited);
                visited[i] = false;
            }
        }
        
        answer = Math.max(answer, cnt);
    }
}