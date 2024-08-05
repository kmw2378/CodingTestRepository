import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long k = sc.nextLong();
        boolean answer = new Solution().solution(n, k);
        System.out.println(answer ? "YES" : "NO");
    }
}
class Solution {
    public boolean solution(long n, long k) {
        long v = n * n - 4 * (k - n - 1);
        long sqrtV = (long)Math.sqrt(v);
        
        if (v < 0 || sqrtV * sqrtV != v) {
            return false;
        }
        
        long m = (long)Math.sqrt(v);
        if (n < m) {
            return false;
        }
        
        return (n + m) % 2 == 0 && (n - m) % 2 == 0;
    }
}