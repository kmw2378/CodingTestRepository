import java.io.*;

public class Main {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = reader.readLine();
        }

        int[] answer = new Solution().solution(words, n);
        for (int e : answer) {
            System.out.println(e);
        }
    }
}

class Solution {
    public int[] solution(String[] words, int n) {
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = checkPalindrome(words[i]);
        }
        return answer;
    }

    private int checkPalindrome(String word) {
        int begin = 0, end = word.length() - 1;

        while (begin < end) {
            if (word.charAt(begin) == word.charAt(end)) {
                begin++;
                end--;
            } else {
                // 한 문자를 제외하고 회문인지 확인
                if (isPalindrome(word, begin + 1, end) || isPalindrome(word, begin, end - 1)) {
                    return 1; // 유사회문
                } else {
                    return 2; // 일반 문자열
                }
            }
        }
        return 0; // 완전한 회문
    }

    // 특정 구간이 회문인지 확인하는 함수
    private boolean isPalindrome(String word, int left, int right) {
        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}