import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int[] aCards = new int[a];
        int[] bCards = new int[b];
        int[] cCards = new int[c];
        for (int i = 0; i < a; i++) {
            aCards[i] = sc.nextInt();
        }
        
        for (int i = 0; i < b; i++) {
            bCards[i] = sc.nextInt();
        }
        
        for (int i = 0; i < c; i++) {
            cCards[i] = sc.nextInt();
        }
        
        Arrays.sort(bCards);
        Arrays.sort(cCards);
        int answer = new Solution().solution(a, b, c, aCards, bCards, cCards);
        System.out.println(answer);
    }
}
class Solution {
    public int solution(int a, int b, int c, int[] aCards, int[] bCards, int[] cCards) {
        int min = Integer.MAX_VALUE;
        for (int aCard : aCards) {
            int bCard = getApproximation(aCard, bCards);
            int cCardFromA = getApproximation(aCard, cCards);
            if (aCard == bCard) {
                min = Math.min(min, getScore(aCard, bCard, cCardFromA));
                continue;
            }
            
            int cCardFromB = getApproximation(bCard, cCards);
            min = min(
                min, 
                getScore(aCard, bCard, cCardFromA),
                getScore(aCard, bCard, cCardFromB)
            );
        }
        
        return min;
    }
    
    private int getApproximation(int n, int[] cards) {
        int left = 0;
        int right = cards.length - 1;
        int result = cards[0];
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (cards[mid] < n) {
                left++;
            } else if (cards[mid] == n) {
                return n;
            } else {
                right--;
            }
            
            if (Math.abs(result - n) > Math.abs(cards[mid] - n)) {
                result = cards[mid];
            }
        }
        
        return result;
    }
    
    private int getScore(int a, int b, int c) {
        return Math.abs(max(a, b, c) - min(a, b, c));
    }
    
    private int max(int a, int b, int c) {
        if (a >= b && a >= c) {
            return a;
        }
        
        return Math.max(b, c);
    }
    
    private int min(int a, int b, int c) {
        if (a <= b && a <= c) {
            return a;
        }
        
        return Math.min(b, c);
    }
}