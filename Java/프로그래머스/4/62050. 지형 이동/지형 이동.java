import java.util.*;

class Solution {
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    public int solution(int[][] land, int height) {
        int n = land.length;
        boolean[][] visited = new boolean[n][n];
        Queue<Edge> queue = new PriorityQueue<>();
        int[][] costs = new int[n][n];
        for (int[] row : costs) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        queue.add(new Edge(0, 0, 0));
        costs[0][0] = 0;

        int totalCost = 0;

        while (!queue.isEmpty()) {
            Edge current = queue.poll();
            if (visited[current.x][current.y]) {
                continue;
            }
            visited[current.x][current.y] = true;
            totalCost += current.cost;  // current는 무조건 방문하는 간선이므로 totalCost에 비용 반영

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) {
                    continue;
                }
                
                int heightDiff = Math.abs(land[current.x][current.y] - land[nx][ny]);
                int nextCost = (heightDiff > height) ? heightDiff : 0;
                if (costs[nx][ny] > nextCost) {
                    costs[nx][ny] = nextCost;
                    queue.add(new Edge(nx, ny, nextCost));
                }
            }
        }

        return totalCost;
    }
}
class Edge implements Comparable<Edge> {
    int x;  // 목적지 x좌표
    int y;  // 목적지 y좌표
    int cost;

    Edge(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }
    
    @Override
    public int compareTo(Edge other) {
        return Integer.compare(cost, other.cost);
    }
}