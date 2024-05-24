import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long m = sc.nextLong();
        
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextLong();
        }
        
        long left = 0L;
        long right = 1_000_000_000_000_000_000L;
        long result = 0L;
        while (left <= right) {
            long mid = (left + right) / 2;
            long count = calculateCount(mid, arr, m);
            
            if (count < m) {
                left = mid + 1;
            } else {
                result = mid;
                right = mid - 1;
            }
        }
        
        System.out.println(result);
    }
    
    private static long calculateCount(long mid, long[] arr, long m) {
        long count = 0;
        for (long e : arr) {
            if (count > m) {
                break;
            }
            
            count += (mid / e);
        }
        
        return count;
    }
}