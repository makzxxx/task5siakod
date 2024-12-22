package task;
import java.util.*;
import static task.PathfindingWithWeights.*;

public class Main {
    public static void main(String[] args) {
        int[][] map = {
                {ROAD, OBSTACLE, ROAD, GROUND, OBSTACLE},
                {ROAD, OBSTACLE, GROUND, SAND, SAND},
                {ROAD, OBSTACLE, GROUND, SAND, OBSTACLE},
                {ROAD, SAND, SAND, ROAD, ROAD}
        };

        int startX = 0, startY = 0, endX = 3, endY = 4;

        List<Cell> path = findPath(map, startX, startY, endX, endY);

        if (path.isEmpty()) {
            System.out.println("Путь не найден.");
        } else {
            System.out.println("Путь найден:");
            for (Cell cell : path) {
                System.out.println("(" + cell.x + ", " + cell.y + ") - Вес: " + cell.cost);
            }
            System.out.println("Финальная стоимость: " + path.getLast().cost);
        }
    }
}