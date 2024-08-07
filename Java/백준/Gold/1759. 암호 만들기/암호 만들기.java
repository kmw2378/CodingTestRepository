import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int l = Integer.parseInt(st.nextToken());
        int c =Integer.parseInt(st.nextToken());
        char[] chars = new char[c];
        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < c; i++) {
            chars[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(chars);
        
        new Solution().solution(l, chars);
    }
}

class Solution {
    public void solution(int l, char[] chars) {
        backtracking(0, 0, l, chars, new StringBuilder());
    }
    
    private void backtracking(int length,
                              int start,
                              int l,
                              char[] chars,
                              StringBuilder sb) {
        if (length == l) {
            printWord(sb.toString());
            return;
        }
        
        for (int i = start; i < chars.length; i++) {
            char c = chars[i];
            if (sb.toString().indexOf(c) == -1) {
                sb.append(c);
                backtracking(length + 1, i, l, chars, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
    
    private void printWord(String word) {
        if (!suitable(word)) {
            return;
        }
        
        System.out.println(word);
    }
    
    private boolean suitable(String word) {
        int vowelCnt = 0;
        for (char c : word.toCharArray()) {
            if (isVowel(c)) {
                vowelCnt++;
            }
        }
        
        return vowelCnt >= 1 && word.length() - vowelCnt >= 2;
    }
    
    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}