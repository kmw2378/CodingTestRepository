import java.util.*;

public class Main {
    static Queue<Integer> queue = new LinkedList<>();;
    static int a;
    static int b;
    static int count = 1;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        a = sc.nextInt();
        b = sc.nextInt();
        
        bfs();
        System.out.println(count);
    }
    
    private static void bfs() {
        queue.add(b);
        
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            String str = String.valueOf(poll);
            
            if (poll == a) {
                return;
            } else if (poll < a) {
                count = -1;
                return;
            }
            
            if (poll % 2 == 0) {
                poll /= 2;
            } else if (str.charAt(str.length() - 1) == '1') {
                poll = Integer.parseInt(str.substring(0, str.length() - 1));
            } else {
                count = -1;
                return;
            }
            
            queue.add(poll);
            count++;
        }
    }
}