import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] percentages = new int[4];
        for (int i = 0; i < 4; i++) {
            percentages[i] = sc.nextInt();
        }
        
        double answer = new Solution().solution(n, percentages);
        System.out.println(answer);
    }
}
class Solution {
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    double simplePathRateSum = 0.;
    
    public double solution(int n, int[] percentages) {
        boolean[][] visited = new boolean[2 * n + 1][2 * n + 1];
        visited[n][n] = true;    // (0, 0) 방문
        backtracking(n, 0, percentages, new int[]{n, n}, new ArrayList<>(), visited);
        return simplePathRateSum;
    }
    
    private void backtracking(int n, 
                              int cnt, 
                              int[] percentages,
                              int[] point,
                              List<Double> pathRates,
                              boolean[][] visited) {
        if (cnt == n) {
            simplePathRateSum += getPathRatesMultipliedValue(pathRates);
            return;
        }
        
        int x = point[0];
        int y = point[1];
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (visited[nx][ny]) {
                continue;
            }
            
            pathRates.add(percentages[i] / 100.);
            visited[nx][ny] = true;
            backtracking(n, cnt + 1, percentages, new int[]{nx, ny}, pathRates, visited);
            visited[nx][ny] = false;
            pathRates.remove(pathRates.size() - 1);
        }
    }
    
    private double getPathRatesMultipliedValue(List<Double> pathRates) {
        double multipliedValue = 1.;
        for (double pathRate : pathRates) {
            multipliedValue *= pathRate;
        }
        
        return multipliedValue;
    }
}