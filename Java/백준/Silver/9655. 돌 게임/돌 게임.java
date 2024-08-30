import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String answer = new Solution().solution(n);
        System.out.println(answer);
    }
}
class Solution {
    public String solution(int n) {
        return n % 2 == 1 ? "SK" : "CY";
    }
}