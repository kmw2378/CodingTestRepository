import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = solution(places[i]);
        }

        return answer;
    }
    
    public int solution(String[] place) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < place.length; i++) {
            for (int j = 0; j < place[i].length(); j++) {
                int p = place[i].charAt(j);
                if (p == 'P') {
                    list.add(new int[]{i, j});
                }
            }
        }
        
        for (int[] current : list) {
            for (int[] target : list) {
                if (current[0] == target[0] && current[1] == target[1]) {
                    continue;
                }
                
                if (getDistance(current, target) > 2) {
                    continue;
                }
                
                if (current[0] == target[0]) {
                    if (getDistance(current, target) == 1) {
                        return 0;
                    }
                    int x = current[0];
                    int minY = Math.min(current[1], target[1]);
                    int maxY = Math.max(current[1], target[1]);
                    for (int y = minY + 1; y < maxY; y++) {
                        if (place[x].charAt(y) != 'X') {
                            return 0;
                        }
                    }
                } else if (current[1] == target[1]) {
                    if (getDistance(current, target) == 1) {
                        return 0;
                    }
                    int y = current[1];
                    int minX = Math.min(current[0], target[0]);
                    int maxX = Math.max(current[0], target[0]);
                    for (int x = minX + 1; x < maxX; x++) {
                        if (place[x].charAt(y) != 'X') {
                            return 0;
                        }
                    }
                } else {
                    int minX = Math.min(current[0], target[0]);// 0
                    int minY = Math.min(current[1], target[1]);// 1
                    int maxX = Math.max(current[0], target[0]);// 1
                    int maxY = Math.max(current[1], target[1]);// 2
                    
                    int m = (current[0] - target[0]) / (current[1] - target[1]);
                    if (m < 0) {
                         if (place[maxX].charAt(maxY) != 'X' || place[minX].charAt(minY) != 'X') {
                            return 0;
                        }                   
                    } else {
                        if (place[minX].charAt(maxY) != 'X' || place[maxX].charAt(minY) != 'X') {
                            return 0;
                        }
                    }
                }
            }
        }
        
        return 1;
    }
    
    private int getDistance(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}