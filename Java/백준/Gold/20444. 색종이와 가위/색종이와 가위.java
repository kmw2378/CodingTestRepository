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
        // 오버플로를 방지하기 위해 long 타입을 사용하여 판별식 계산
        long discriminant = n * n - 4 * (k - n - 1);
        if (discriminant < 0) {
            return false;
        }
        
        // 판별식의 제곱근 계산
        long sqrtDiscriminant = (long)Math.sqrt(discriminant);
        if (sqrtDiscriminant * sqrtDiscriminant != discriminant) {
            return false;
        }
        
        long m = sqrtDiscriminant;
        long x1 = (n + m) / 2;
        long x2 = (n - m) / 2;
        
        // x1과 x2가 정수인지 확인
        if ((n + m) % 2 == 0 && x1 >= 0 && x1 <= n) {
            return true;
        }
        if ((n - m) % 2 == 0 && x2 >= 0 && x2 <= n) {
            return true;
        }
        
        return false;
    }
}