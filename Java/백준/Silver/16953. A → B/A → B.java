import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int count = 1;
        while (a < b) {
            String str = String.valueOf(b);
            
            if (b % 2 == 0) {
                b /= 2;
            } else if (str.charAt(str.length() - 1) == '1') {
                b = Integer.parseInt(str.substring(0, str.length() - 1));
            } else {
                count = -1;
                break;
            }
            
            count++;
        }
        
        if (b < a) {
            count = -1;
        }
        System.out.println(count);
    }
}