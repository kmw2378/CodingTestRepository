import java.io.*;

public class Main {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();
        backtracking(n, sb);
    }
    
    static void backtracking(int n, StringBuilder sb) {
        if (sb.length() == n) {
            System.out.println(sb.toString());
            System.exit(0);
        }
        
        for (int i = 1; i <= 3; i++) {
            sb.append(i);
            if (satisfied(sb)) {
                backtracking(n, sb);
            }
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
    static boolean satisfied(StringBuilder sb) {
        String s = sb.toString();
        for (int i = 1; i <= s.length() / 2; i++) {
            String front = s.substring(s.length() - i * 2, s.length() - i);
            String back = s.substring(s.length() - i);
            if (front.equals(back)) {
                return false;
            }
        }
        return true;
    }
}