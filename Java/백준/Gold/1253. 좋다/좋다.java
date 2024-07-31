import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        int[] arr = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        int result = new Solution().solution(n, arr);
        System.out.println(result);
    }
}
class Solution {
    public int solution(int n, int[] arr) {
        Arrays.sort(arr);
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int current = arr[i];
            int left = 0;
            int right = n - 1;
            while (true) {
                if (left == i) {
                    left++;
                }
                if (right == i) {
                    right--;
                }
                
                if (left >= right) {
                    break;
                }
                
                int sum = arr[left] + arr[right];
                if (sum < current) {
                    left++;
                } else if (sum == current) {
                    cnt++;
                    break;
                } else {
                    right--;
                }
            }
        }
        
        return cnt;
    }
}