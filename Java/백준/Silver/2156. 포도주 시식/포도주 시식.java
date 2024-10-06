import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }
        
        int answer = new Solution().solution(n, arr);
        System.out.println(answer);
    }
}

class Solution {
    public int solution(int n, int[] arr) {
        int[] dp = new int[n + 1];
        dp[1] = arr[1];
        if (n >= 2) {
            dp[2] = arr[1] + arr[2];
        }
        
        for (int i = 3; i <= n; i++) {
            dp[i] = max(dp[i - 1], dp[i - 2] + arr[i], dp[i - 3] + arr[i - 1] + arr[i]);
        }
        
        return dp[n];
    }
    
    private int max(int a, int b, int c) {
        if (a > b && a > c) {
            return a;
        }
        
        return Math.max(b, c);
    }
}