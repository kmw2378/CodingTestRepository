import java.util.*;
import java.io.*;

class Main {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final int INF = 32768;
    static List<int[]> points;
    static Set<int[]> set;
    
    public static void main(String[] args) throws IOException {
        int t = inputInteger();
        for (int i = 1; i <= t; i++) {
            int n = inputInteger();
            int[] startPoint = inputPoint();
            set = new HashSet<>();
            points = new ArrayList<>();
            for (int j = 1; j <= n + 1; j++) {
                points.add(inputPoint());
            }
            dfs(startPoint);
            int[] endPoint = points.get(n);
            int endX = endPoint[0];
            int endY = endPoint[1];
            if (set.contains(endPoint)) {
                System.out.println("happy");
            } else {
                System.out.println("sad");
            }
        }
        
    }
    
    private static int inputInteger() throws IOException {
        return Integer.parseInt(reader.readLine());
    }
    
    private static int[] inputPoint() throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
    }
    
    private static void dfs(int[] startPoint) {
        set.add(startPoint);
        for (int[] point : points) {
            if (!set.contains(point) && getDistance(startPoint, point) <= 1000) {
                dfs(point);
            }
        }
    }
    
    private static int getDistance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}