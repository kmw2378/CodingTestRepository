import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int h = sc.nextInt();
        
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        
        int answer = new Solution().solution(arr, m, h);
        System.out.println(answer);
    }
}

class Solution {
    int answer = 0;
    int[] homePoint;
    List<int[]> milkPoints;
    
    public int solution(int[][] arr, int m, int h) {
        homePoint = getHomePoint(arr);
        milkPoints = getMilkPoints(arr);
        int n = arr.length;
        backtacking(m, h, 0, homePoint, new boolean[n][n]);
        return answer;
    }
    
    private int[] getHomePoint(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 1) {
                    return new int[]{i, j};
                }
            }
        }
        
        throw new IllegalArgumentException("start not found");
    }
    
    private List<int[]> getMilkPoints(int[][] arr) {
        List<int[]> milkPoints = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 2) {
                    milkPoints.add(new int[]{i, j});
                }
            }
        }
        
        return milkPoints;
    }
    
    private void backtacking(int remainHealth, 
                             int h, 
                             int milkCnt,
                             int[] current,
                             boolean[][] visited) {
        if (getDistance(current, homePoint) <= remainHealth) {
            answer = Math.max(answer, milkCnt);
        }
        
        for (int[] milkPoint : milkPoints) {
            if (visited[milkPoint[0]][milkPoint[1]]) {
                continue;
            }
            
            int distance = getDistance(current, milkPoint);
            if (remainHealth - distance < 0) {
                continue;
            }
            
            visited[milkPoint[0]][milkPoint[1]] = true;
            backtacking(remainHealth - distance + h, h, milkCnt + 1, milkPoint, visited);
            visited[milkPoint[0]][milkPoint[1]] = false;
        }
    }
    
    private int getDistance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}