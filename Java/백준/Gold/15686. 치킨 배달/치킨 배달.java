import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        
        int answer = new Solution().solution(arr, m);
        System.out.println(answer);
    }
}
class Solution {
    List<int[]> housePoints;
    List<int[]> chickenPoints;
    int answer = Integer.MAX_VALUE;
    public int solution(int[][] arr, int m) {
        int n = arr.length;
        housePoints = getPoints(arr, 1);
        chickenPoints = getPoints(arr, 2);
        backtracking(0, m, 0, new boolean[chickenPoints.size()]);
        return answer;
    }
    
    private void backtracking(int cnt,
                              int m, 
                              int currentIdx,
                              boolean[] visited) {
        if (cnt == m) {
            answer = Math.min(answer, getDistanceSum(visited));
            return;
        }
        
        for (int i = currentIdx; i < chickenPoints.size(); i++) {
            visited[i] = true;
            backtracking(cnt + 1, m, i + 1, visited);
            visited[i] = false;
        }
    }
    
    private List<int[]> getPoints(int[][] arr, int target) {
        List<int[]> points = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == target) {
                    points.add(new int[]{i, j});
                }
            }
        }
        
        return points;
    }
    
    private int getDistanceSum(boolean[] visited) {
        int sum = 0;
        for (int[] housePoint : housePoints) {
            int distance = Integer.MAX_VALUE;
            for (int i = 0; i < chickenPoints.size(); i++) {
                if (!visited[i]) {
                    continue;
                }
                
                int[] chickenPoint = chickenPoints.get(i);
                distance = Math.min(distance, getDistance(housePoint, chickenPoint));
            }
            
            sum += distance;
        }
        
        return sum;
    }
    
    private int getDistance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}