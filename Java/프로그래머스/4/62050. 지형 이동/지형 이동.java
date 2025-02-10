import java.util.*;

class Solution {
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    public int solution(int[][] land, int height) {
        int n = land.length;
        boolean[][] visited = new boolean[n][n];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.cost));
        int[][] costs = new int[n][n];
        for (int[] row : costs) Arrays.fill(row, Integer.MAX_VALUE);

        pq.add(new Edge(0, 0, 0));
        costs[0][0] = 0;

        int totalCost = 0;

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (visited[current.x][current.y]) continue;
            visited[current.x][current.y] = true;
            totalCost += current.cost;

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (visited[nx][ny]) continue;

                int heightDiff = Math.abs(land[current.x][current.y] - land[nx][ny]);
                int nextCost = (heightDiff > height) ? heightDiff : 0;

                if (costs[nx][ny] > nextCost) {
                    costs[nx][ny] = nextCost;
                    pq.add(new Edge(nx, ny, nextCost));
                }
            }
        }

        return totalCost;
    }

    static class Edge {
        int x, y, cost;

        Edge(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
}