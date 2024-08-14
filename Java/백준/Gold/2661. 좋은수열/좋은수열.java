import java.io.*;
import java.util.*;
 
public class Main {
    static int length;
    static char[] arr = {'1', '2', '3'};
    static boolean flag;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        length = Integer.parseInt(st.nextToken());
 
        backTracking("");
    }
 
    static void backTracking(String str) {
        if(flag) return;
 
        if (str.length() == length ) {
            flag = true;
            System.out.print(str);
        }
 
        /*if(str.length() == length){
            System.out.print(str);
            System.exit(0);
            //return;
        }*/
 
        for (int i = 0; i < arr.length; i++) {
            if (isConditionTrue(str + arr[i])) {
                backTracking(str + arr[i]);
            }
        }
 
 
    }
 
    static boolean isConditionTrue(String str) {
        for (int i = 1; i <= str.length() / 2; i++) {
            if (str.substring(str.length() - i).equals(str.substring(str.length() - 2 * i, str.length() - i)))
                return false;
        }
        return true;
    }
}
