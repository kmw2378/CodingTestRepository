import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        int[] operatorCnts = new int[4];
        for (int i = 0; i < 4; i++) {
            operatorCnts[i] = sc.nextInt();
        }
        
        int[] answer = new Solution().solution(arr, operatorCnts);
        System.out.println(answer[0]);
        System.out.println(answer[1]);
    }
}
class Solution {
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    public int[] solution(int[] arr, int[] operatorCnts) {
        backtracking(arr, 1, arr[0], operatorCnts);
        return new int[]{max, min};
    }
    
    private void backtracking(int[] arr, 
                              int currentIdx, 
                              int current,
                              int[] operatorCnts) {
        if (arr.length == currentIdx) {
            min = Math.min(min, current);
            max = Math.max(max, current);
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            int cnt = operatorCnts[i];
            if (cnt == 0) {
                continue;
            }
            
            operatorCnts[i]--;
            backtracking(arr, currentIdx + 1, getResult(current, i, arr[currentIdx]), operatorCnts);
            operatorCnts[i]++;
        }
    }
    
    private int getResult(int current, int i, int next) {
        if (i == 0) {
            return current + next;
        } else if (i == 1) {
            return current - next;
        } else if (i == 2) {
            return current * next;
        } else if (i == 3) {
            return current / next;
        } else {
            throw new IllegalArgumentException("Invalid operator index");
        }
    }
}