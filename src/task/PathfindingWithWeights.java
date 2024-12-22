package task;
import java.util.*;

public class PathfindingWithWeights {

    private static final int INF = Integer.MAX_VALUE;

    public static final int ROAD = 1;
    public static final int GROUND = 2;
    public static final int SAND = 3;
    public static final int OBSTACLE = -1;

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public static List<Cell> findPath(int[][] map, int startX, int startY, int endX, int endY) {
        int rows = map.length;
        int cols = map[0].length;

        int[][] distance = new int[rows][cols];
        for (int[] row : distance){
            Arrays.fill(row, INF);
        }

        boolean[][] visited = new boolean[rows][cols];
        PriorityQueue<Cell> queue = new PriorityQueue<>();

        distance[startX][startY] = 0;
        queue.add(new Cell(startX, startY, 0));

        while (!queue.isEmpty()) {
            Cell current = queue.poll();

            if (visited[current.x][current.y]) continue;
            visited[current.x][current.y] = true;

            if (current.x == endX && current.y == endY) break;

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < rows && ny < cols && !visited[nx][ny] && map[nx][ny] != OBSTACLE) {
                    int newCost = distance[current.x][current.y] + map[nx][ny];
                    if (newCost < distance[nx][ny]) {
                        distance[nx][ny] = newCost;
                        queue.add(new Cell(nx, ny, newCost));
                    }
                }
            }
        }

        if (distance[endX][endY] == INF) return Collections.emptyList();
        List<Cell> path = new ArrayList<>();
        int cx = endX, cy = endY;

        while (!(cx == startX && cy == startY)) {
            path.add(new Cell(cx, cy, distance[cx][cy]));

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && ny >= 0 && nx < rows && ny < cols &&
                        distance[cx][cy] - map[cx][cy] == distance[nx][ny]) {
                    cx = nx;
                    cy = ny;
                    break;
                }
            }
        }

        path.add(new Cell(startX, startY, 0));
        Collections.reverse(path);
        return path;
    }
}