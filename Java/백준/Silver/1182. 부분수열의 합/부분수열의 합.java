import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        int answer = new Solution().solution(arr, s);
        System.out.println(answer);
    }
}

class Solution {
    int answer = 0;
    
    public int solution(int[] arr, int s) {
        backtrack(arr, s, 0, 0);
        return answer;
    }
    
    private void backtrack(int[] arr, int s, int idx, int sum) {
        if (idx == arr.length) {
            return;
        }
        
        // 현재 원소를 포함하지 않는 경우
        backtrack(arr, s, idx + 1, sum);
        
        // 현재 원소를 포함하는 경우
        sum += arr[idx];
        if (sum == s) {
            answer++;
        }
        backtrack(arr, s, idx + 1, sum);
    }
}
