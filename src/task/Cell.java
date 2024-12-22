package task;
public class Cell implements Comparable<Cell> {
    public int x, y;
    public int cost;

    public Cell(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Cell other) {
        return Integer.compare(this.cost, other.cost);
    }
}