package life;

public class Universe {

    private boolean[][] universe;

    public Universe(int size) {
        this.universe = new boolean[size][size];
    }

    public boolean[][] getUniverse() {
        return universe;
    }

    public void setUniverse(boolean[][] universe) {
        this.universe = universe;
    }
}
