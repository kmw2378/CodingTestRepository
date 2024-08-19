import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;

        List<List<String>> mineralList = new ArrayList<>();
        for (int i = 1; i <= minerals.length / 5 + 1; i++) {
            List<String> tempList = new ArrayList<>();
            for (int j = 5 * (i - 1); j < 5 * i && j < minerals.length; j++) {
                tempList.add(minerals[j]);
            }

            mineralList.add(tempList);
        }

        for (int i = 0; i < picks.length; i++) {

            while (picks[i] > 0 && !mineralList.isEmpty()) {

                int max = 0;
                int idx = 0;
                int remainPicksCnt = getRemainPicksCnt(picks);
                int size = mineralList.size();
                for (int j = 0; j < Math.min(size, remainPicksCnt); j++) {
                    int fatigueSum = getWorstFatigueSum(mineralList.get(j));
                    if (max <= fatigueSum) {
                        idx = max == fatigueSum ? (getFatigueSum(i, mineralList.get(j)) < getFatigueSum(i, mineralList.get(idx)) ? j : idx) : j;
                        max = fatigueSum;
                    }
                }

                answer += getFatigueSum(i, mineralList.remove(idx));
                picks[i]--;
            }
        }

        return answer;
    }

    private int getRemainPicksCnt(int[] picks) {
        int sum = 0;
        for (int pick : picks) {
            sum += pick;
        }

        return sum;
    }

    private int getWorstFatigueSum(List<String> minerals) {

        int sum = 0;
        for (String mineral : minerals) {
            sum += Math.pow(5, 2 - getRank(mineral));
        }

        return sum;
    }

    private int getFatigueSum(int pick, List<String> minerals) {

        int sum = 0;
        for (String mineral : minerals) {
            sum += Math.pow(5, Math.max(pick - getRank(mineral), 0));
        }

        return sum;
    }

    private int getRank(String mineral) {

        int rank;
        if (mineral.equals("diamond")) {
            rank = 0;
        } else if (mineral.equals("iron")) {
            rank = 1;
        } else {
            rank = 2;
        }
        return rank;
    }
}