import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        for (int i = 1; i <= c; i++) {
            int[][] positions = new int[11][11];
            for (int x = 0; x < 11; x++) {
                for (int y = 0; y < 11; y++) {
                    positions[x][y] = sc.nextInt();
                }
            }
            
            int answer = new Solution().solution(positions);
            System.out.println(answer);
        }
    }
}

// positions[i][j] : i번 선수가 j번 포지션에 뛸때 능력
class Solution {
    int answer = 0;
    public int solution(int[][] positions) {
        backtracking(positions, 0, 0, new boolean[11]);
        return answer;
    }
    
    private void backtracking(int[][] positions, 
                              int sum,
                              int depth,
                              boolean[] visited) {
        if (depth == 11) {
            answer = Math.max(sum, answer);
            return;
        }
        
        for (int i = 0; i < 11; i++) {
            if (visited[i] || positions[depth][i] == 0) {
                continue;
            }
            
            visited[i] = true;
            backtracking(positions, sum + positions[depth][i], depth + 1, visited);
            visited[i] = false;
        }
    }
}