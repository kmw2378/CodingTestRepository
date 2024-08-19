import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        
        int answer = new Solution().solution(arr, n);
        System.out.println(answer);
    }
}

class Solution {
    int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] arr, int n) {
        backtracking(arr, new ArrayList<>(), new ArrayList<>(), 0);
        return answer;
    }
    
    private void backtracking(int[][] arr, 
                              List<Integer> teamA,
                              List<Integer> teamB,
                              int currentIdx) {
        if (arr.length == currentIdx) {
            answer = Math.min(answer, Math.abs(getSum(arr, teamA) - getSum(arr, teamB)));
            return;
        }
        
        if (teamA.size() == arr.length / 2) {
            teamB.add(currentIdx);
            backtracking(arr, teamA, teamB, currentIdx + 1);
            teamB.remove(teamB.size() - 1);
            return;
        }
        
        if (teamB.size() == arr.length / 2) {
            teamA.add(currentIdx);
            backtracking(arr, teamA, teamB, currentIdx + 1);
            teamA.remove(teamA.size() - 1);
            return;
        }
        
        // A팀에 자신 포함
        teamA.add(currentIdx);
        backtracking(arr, teamA, teamB, currentIdx + 1);
        teamA.remove(teamA.size() - 1);
        
        // B팀에 자신 포함
        teamB.add(currentIdx);
        backtracking(arr, teamA, teamB, currentIdx + 1);
        teamB.remove(teamB.size() - 1);
    }
    
    private int getSum(int[][] arr, List<Integer> team) {
        int sum = 0;
        for (int i : team) {
            for (int j : team) {
                sum += arr[i][j];
            }
        }
        
        return sum;
    }
}