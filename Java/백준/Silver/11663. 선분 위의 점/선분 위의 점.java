import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        
        for (int i = 1; i <= m; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            
            if (start > arr[n - 1] && end > arr[n - 1] || start < arr[0] && end < arr[0]) {
               System.out.println(0);
               continue; 
            }
            
            int left = 0;
            int right = n - 1;
            int result1 = 0;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (start > arr[mid]) {
                    left = mid + 1;
                } else {
                    result1 = mid;
                    right = mid - 1;
                }
            }
            
            left = 0;
            right = n - 1;
            int result2 = 0;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (end >= arr[mid]) {
                    result2 = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            
            System.out.println(result2 - result1 + 1);
        }
    }
}