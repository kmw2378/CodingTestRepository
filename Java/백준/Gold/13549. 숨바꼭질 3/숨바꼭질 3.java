import java.util.*;
import java.util.function.Function;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int answer = new Solution().solution(n, k);
        System.out.println(answer);
    }
}
class Route {
    int time;
    Function<Integer, Integer> function;
    
    Route(int time, Function<Integer, Integer> function) {
        this.time = time;
        this.function = function;
    }
}

class Solution {
    List<Route> routes = List.of(
        new Route(1, e -> e - 1),
        new Route(1, e -> e + 1),
        new Route(0, e -> e * 2)
    );
    
    public int solution(int n, int k) {
        return bfs(n, k);
    }
    
    private int bfs(int start, int destination) {
        int[] costs = new int[100_001];
        Arrays.fill(costs, Integer.MAX_VALUE);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        costs[start] = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == destination) {
                break;
            }
            for (Route route : routes) {
                int next = route.function.apply(current);
                if (next < 0 || next > 100_000) {
                    continue;
                }
                
                if (costs[next] > costs[current] + route.time) {
                    costs[next] = costs[current] + route.time;
                    queue.add(next);    
                }
            }
        }
        
        return costs[destination];
    }
}