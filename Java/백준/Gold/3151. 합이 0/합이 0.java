import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        int[] counts = new int[40_001];
        long answer = 0L;
        for (int i = 0; i < n; i++) {
            answer += counts[20_000 - arr[i]];
            for (int j = 0; j < i; j++) {
                counts[20_000 + arr[i] + arr[j]]++;
            }
        }
        
        System.out.println(answer);
    }
}