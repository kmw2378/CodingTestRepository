import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = sc.nextInt();
        
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        Arrays.sort(arr);
        
        int left = 1;
        int right = arr[n - 1];
        int result = 0;
        while (left <= right) {
            int position = 0;
            int mid = (left + right) / 2;
            int count = 1;
            for (int i = 0; i < n; i++) {
                if (arr[i] - arr[position] >= mid) {
                    position = i;
                    count++;
                }
            }
            
            if (count >= c) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        System.out.println(result);
    }
}