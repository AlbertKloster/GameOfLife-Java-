package life;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameOfLife extends JFrame {


    private int delay = 500;
    private final ImageIcon playImg = new ImageIcon("C:\\Users\\ak\\IdeaProjects\\Game of Life (Java)\\Game of Life (Java)\\task\\src\\life\\assets\\img\\play.png");
    private final ImageIcon pauseImg = new ImageIcon("C:\\Users\\ak\\IdeaProjects\\Game of Life (Java)\\Game of Life (Java)\\task\\src\\life\\assets\\img\\pause.png");
    private boolean isPlay = true;


    public GameOfLife() {
        super("Game of Life");

        long generation = 0;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(636, 690);
        setLocationRelativeTo(null);

        Container container = getContentPane();
        setLayout(new BorderLayout());

        JLabel generationImgLabel = new JLabel(new ImageIcon("C:\\Users\\ak\\IdeaProjects\\Game of Life (Java)\\Game of Life (Java)\\task\\src\\life\\assets\\img\\generation.png"));
        JLabel generationLabel = new JLabel();
        generationLabel.setName("GenerationLabel");
        generationLabel.setText("Generations: " + 0);

        JLabel aliveImgLabel = new JLabel(new ImageIcon("C:\\Users\\ak\\IdeaProjects\\Game of Life (Java)\\Game of Life (Java)\\task\\src\\life\\assets\\img\\alive.png"));
        JLabel aliveLabel = new JLabel();
        aliveLabel.setName("AliveLabel");
        aliveLabel.setText("Alive: " + 0);

        JLabel speedLabel = new JLabel();
        speedLabel.setText("Speed");

        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 10, 500, 200);
        speedSlider.setInverted(true);


        JToggleButton playToggleButton = new JToggleButton();
        playToggleButton.setName("PlayToggleButton");
        playToggleButton.setBorderPainted(false);
        playToggleButton.setPreferredSize(new Dimension(24, 24));
        playToggleButton.addActionListener(this::handleToggle);
        playToggleButton.setIcon(pauseImg);

        JButton resetButton = new JButton();
        resetButton.setName("ResetButton");
        resetButton.setBorderPainted(false);
        resetButton.setPreferredSize(new Dimension(24, 24));
        ImageIcon resetImg = new ImageIcon("C:\\Users\\ak\\IdeaProjects\\Game of Life (Java)\\Game of Life (Java)\\task\\src\\life\\assets\\img\\replay.png");
        resetButton.setIcon(resetImg);

        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        labelsPanel.add(generationImgLabel);
        labelsPanel.add(generationLabel);
        labelsPanel.add(aliveImgLabel);
        labelsPanel.add(aliveLabel);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        controlPanel.add(speedLabel);
        controlPanel.add(speedSlider);
        controlPanel.add(playToggleButton);
        controlPanel.add(resetButton);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(labelsPanel, BorderLayout.WEST);
        headerPanel.add(controlPanel, BorderLayout.EAST);
        container.add(headerPanel, BorderLayout.NORTH);

        CellsPanel cellsPanel = new CellsPanel();
        speedSlider.addChangeListener(this::setDelay);
        resetButton.addActionListener(e -> cellsPanel.reset());

        container.add(cellsPanel, BorderLayout.CENTER);
        setVisible(true);

        while (true) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (!isPlay) continue;

            generationLabel.setText("Generations: " + generation++);
            aliveLabel.setText("Alive: " + cellsPanel.getAlive());
            cellsPanel.evolve();
            repaint();
        }

    }


    private void handleToggle(ActionEvent e) {
        JToggleButton toggleButton = (JToggleButton) e.getSource();
        toggleButton.setIcon(isPlay ? playImg : pauseImg);
        isPlay = !isPlay;
    }

    private void setDelay(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        delay = source.getValue();
    }

}
