import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Egg[] eggs = new Egg[n];
        for (int i = 0; i < n; i++) {
            int health = sc.nextInt();
            int weight = sc.nextInt();
            eggs[i] = new Egg(health, weight);
        }
        
        int answer = new Solution().solution(eggs);
        System.out.println(answer);
    }
}

class Solution {
    int answer = 0;
    public int solution(Egg[] eggs) {
        backtracking(eggs, 0);
        return answer;
    }
    
    private void backtracking(Egg[] eggs, int left) {
        if (left == eggs.length) {
            int cnt = calculateBreakCnt(eggs);
            answer = Math.max(cnt, answer);
            return;
        }
        
        // 손에 들고있는 계란이 깨진 경우
        if (eggs[left].isBreak()) {
            backtracking(eggs, left + 1);
            return;
        }
        
        boolean existEgg = false;
        for (int i = 0; i < eggs.length; i++) {
            // 자기 자신을 치거나 타겟 계란이 깨져있는 경우
            if (i == left || eggs[i].isBreak()) {
                continue;
            }
            
            existEgg = true;
            
            // 왼쪽 계란으로 다른 계란을 친다.
            eggs[i].health -= eggs[left].weight;
            eggs[left].health -= eggs[i].weight;
            
            // 한 칸 오른쪽 계란을 들고 2번 과정 다시 진행
            backtracking(eggs, left + 1);
            
            // 복구
            eggs[i].health += eggs[left].weight;
            eggs[left].health += eggs[i].weight;
        }
        
        // 깨지지 않은 계란이 없는 경우
        if (!existEgg) {
            backtracking(eggs, eggs.length);
        }
    }
    
    private int calculateBreakCnt(Egg[] eggs) {
        int cnt = 0;
        for (Egg egg : eggs) {
            if (egg.isBreak()) {
                cnt++;
            }
        }
        
        return cnt;
    }
}

class Egg {
    int health;
    int weight;
    
    public Egg(int health, int weight) {
        this.health = health;
        this.weight = weight;
    }
    
    public boolean isBreak() {
        return health <= 0;
    }
}