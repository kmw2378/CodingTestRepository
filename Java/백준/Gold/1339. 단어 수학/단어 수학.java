import java.util.*;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = sc.nextLine();
        }
        
        long answer = new Solution().solution(n, words);
        System.out.println(answer);
    }
}
class Solution {
    long answer = 0L;
    
    public long solution(int n, String[] words) {
        List<Character> chars = initChars(words);
        backtracking(0, 9, chars, words, new HashMap<>());
        return answer;
    }
    
    private void backtracking(int cnt,
                              int max,
                              List<Character> chars,
                              String[] words,
                              Map<Character, Integer> map) {
        if (chars.size() == cnt) {
            answer = Math.max(answer, calculateSum(map, words));
            return;
        }
        
        for (char c : chars) {
            if (!map.containsKey(c)) {
                map.put(c, max);
                backtracking(cnt + 1, max - 1, chars, words, map);
                map.remove(c);
            }
        }
    }
    
    private List<Character> initChars(String[] words) {
        Set<Character> set = new HashSet<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                set.add(c);
            }
        }
        
        return set.stream()
            .collect(Collectors.toList());
    }
    
    private long calculateSum(Map<Character, Integer> map,
                             String[] words) {
        long sum = 0L;
        for (String word : words) {
            long partialSum = 0L;
            for (char c : word.toCharArray()) {
                partialSum = partialSum * 10 + map.get(c);
            }
            sum += partialSum;
        }
        
        return sum;
    }
}