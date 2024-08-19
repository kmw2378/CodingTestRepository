import java.util.*;

class Solution {
    public int solution(String word) {
        int answer = 0;
        Map<Character, Integer> map = initMap();
        for (int i = 0; i < word.length(); i++) {
            int n = map.get(word.charAt(i));
            answer += ((n - 1) * getSum(5 - i - 1) + 1);
        }
        
        return answer;
    }
    
    private int getSum(int n) {
        return ((int)Math.pow(5, n + 1) - 1) / 4;
    }
    
    private Map<Character, Integer> initMap() {
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 1);
        map.put('E', 2);
        map.put('I', 3);
        map.put('O', 4);
        map.put('U', 5);
        
        return map;
    }
}