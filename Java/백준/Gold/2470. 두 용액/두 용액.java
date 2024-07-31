import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        int[] arr = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        
        int[] result = new Solution().solution(n, arr);
        System.out.printf("%d %d\n", result[0], result[1]);
    }
}

class Solution {
    public int[] solution(int n, int[] arr) {
        Arrays.sort(arr);
        int left = 0;
        int right = n - 1;
        int[] result = new int[2];
        int min = Integer.MAX_VALUE;
        
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (min > Math.abs(sum)) {
                min = Math.abs(sum);
                result[0] = arr[left];
                result[1] = arr[right];
            }
            
            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }
        
        return result;
    }
}