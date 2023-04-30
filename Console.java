package life;

import java.util.Scanner;

public class Console {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        UniverseHandler universeHandler = new UniverseHandler(size);
        for (int i = 0; i < 10; i++) {
            Util.clearConsole();
            universeHandler.evolve();
            System.out.printf("Generation #%d\n", universeHandler.getGeneration());
            System.out.printf("Alive: %d\n", universeHandler.countAllLivingCells());
            print(universeHandler.getUniverse());
        }
    }

    private static void print(Universe universe) {
        for (boolean[] row : universe.getUniverse()) {
            for (boolean cell : row) {
                System.out.print(cell ? "O" : " ");
            }
            System.out.println();
        }
    }
}
