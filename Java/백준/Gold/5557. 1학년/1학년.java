import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        long answer = new Solution().solution(n, arr);
        System.out.println(answer);
    }
}

class Solution {
    long[][] mems;

    public long solution(int n, int[] arr) {
        mems = new long[n][21];
        for (long[] mem : mems) {
            Arrays.fill(mem, -1);
        }

        return backtracking(1, arr, arr[0]);
    }

    private long backtracking(int depth, int[] arr, int result) {
        if (result < 0 || result > 20) {
            return 0;
        }

        if (depth == arr.length - 1) {
            return result == arr[depth] ? 1 : 0;
        }

        if (mems[depth][result] != -1) {
            return mems[depth][result];
        }

        long res1 = backtracking(depth + 1, arr, result + arr[depth]);
        long res2 = backtracking(depth + 1, arr, result - arr[depth]);
        mems[depth][result] = res1 + res2;
        return mems[depth][result];
    }
}