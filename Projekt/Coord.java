
public class Coord {
    private int x;
    private int y;
    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Object c) {
        if (c instanceof Coord) {
            Coord coord = (Coord) c;
            return coord.x == x && coord.y == y;
        }
        return false;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}