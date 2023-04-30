package life;

import javax.swing.*;
import java.awt.*;

public class CellsPanel extends JPanel {

    private final UniverseHandler universeHandler;

    public CellsPanel() {
        setSize(600, 600);
        universeHandler = new UniverseHandler(60);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);
        boolean[][] universe = universeHandler.getUniverse().getUniverse();

        int size = universe.length;
        int cellDimension = 10;
        int offset = 10;
        for (int row = 0; row < size; row++) {
            int y = offset + row * cellDimension;
            for (int column = 0; column < size; column++) {
                int x = offset + column * cellDimension;
                g2.setColor(universe[row][column] ? new Color(9, 121, 105) : new Color(236, 255, 220));
                g2.fillRect(x, y, cellDimension, cellDimension);
            }
        }
    }

    public void evolve() {
        universeHandler.evolve();
    }

    public void reset() {
        universeHandler.createRandomUniverse();
    }

    public long getAlive() {
        boolean[][] universe = universeHandler.getUniverse().getUniverse();
        long count = 0;
        for (boolean[] row : universe) {
            for (boolean cell : row) {
                if (cell) count++;
            }
        }
        return count;
    }

}
