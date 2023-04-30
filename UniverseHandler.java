package life;

import java.util.List;
import java.util.Random;

public class UniverseHandler {

    private final Universe universe;
    private long generation = 1;
    public UniverseHandler(int size) {
        this.universe = new Universe(size);
        createRandomUniverse();
    }

    public Universe getUniverse() {
        return universe;
    }

    public void createRandomUniverse() {
        Random random = new Random();
        for (boolean[] row : universe.getUniverse()) {
            for (int column = 0; column < row.length; column++) {
                row[column] = random.nextBoolean();
            }
        }
    }

    public long getGeneration() {
        return generation;
    }

    public void evolve() {
        boolean[][] oldUniverse = universe.getUniverse();
        int size = oldUniverse.length;
        boolean[][] newUniverse = new boolean[size][size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                newUniverse[row][column] = isLiving(oldUniverse[row][column], countLivingNeighbours(row, column));
            }
        }
        universe.setUniverse(newUniverse);
        generation++;
    }

    public long countAllLivingCells() {
        long count = 0;
        for (boolean[] row : universe.getUniverse()) {
            for (boolean cell : row) {
                if (cell) count++;
            }
        }
        return count;
    }

    private boolean isLiving(boolean living, long livingNeighbours) {
        return living ?
                !(livingNeighbours < 2 || livingNeighbours > 3) :
                livingNeighbours == 3;
    }

    private long countLivingNeighbours(int row, int column) {
        return getNeighbourCells(row, column).stream().filter(cell -> cell).count();
    }
    
    private List<Boolean> getNeighbourCells(int row, int column) {
        int size = universe.getUniverse().length;
        int up = (size + row - 1) % size;
        int down = (row + 1) % size;
        int left = (size + column - 1) % size;
        int right = (column + 1) % size;

        return List.of(
                getCellByCoordinate(up, left),
                getCellByCoordinate(up, column),
                getCellByCoordinate(up, right),
                getCellByCoordinate(row, left),
                getCellByCoordinate(row, right),
                getCellByCoordinate(down, left),
                getCellByCoordinate(down, column),
                getCellByCoordinate(down, right)
        );
    }
    
    private Boolean getCellByCoordinate(int row, int column) {
        return universe.getUniverse()[row][column];
    }

}
