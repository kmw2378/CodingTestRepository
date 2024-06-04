import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int l = sc.nextInt();
        
        int[] arr = new int[n + 2];
        arr[0] = 0;
        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }
        arr[n + 1] = l;
        
        Arrays.sort(arr);
        int left = 1;
        int right = 1000;
        int result = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int count = 0;
            for (int i = 1; i <= n + 1; i++) {
                int diff = arr[i] - arr[i - 1];
                if (diff % mid == 0) {
                    count += (diff / mid - 1); 
                } else {
                    count += diff / mid;
                }
            }
            
            if (count > m) {
                left = mid + 1;
            } else {
                result = mid;
                right = mid - 1;
            }
        }
        
        System.out.println(result);
    }
}