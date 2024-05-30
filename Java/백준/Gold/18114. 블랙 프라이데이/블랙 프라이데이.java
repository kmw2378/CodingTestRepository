import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = sc.nextInt();
        
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            if (arr[i] == c) {
                System.out.println(1);
                return;
            }
        }
        
        Arrays.sort(arr);
        for (int i = 0; i < n - 1; i++) {
            int left = i;
            int right = n - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (i == mid) {
                    break;
                }
                int sum = arr[i] + arr[mid];
                if (sum == c) {
                    System.out.println(1);
                    return;
                }
                
                if (sum < c) {
                    for (int j = i + 1; j < mid; j++) {
                        if (sum + arr[j] == c) {
                            System.out.println(1);
                            return;
                        }
                    }
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        System.out.println(0);
    }
}