/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameOfLifeGUI extends JFrame implements ActionListener {

    public static void main(String[] args) {
        GameOfLifeGUI gui = new GameOfLifeGUI();
    }

    /**
     * Icon for an empty position in the grid and where there is a "thing"
     */
    private final ImageIcon II_EMPTY = new ImageIcon("src/empty.gif");
    private final ImageIcon II_THING = new ImageIcon("src/unknown.gif");
    /**
     * The gGameOfLife-graph this class should display
     */
    private GameOfLifeGraph golGraph;
    /**
     * The grid, i.e., the field containing the images to display.
     */
    private JLabel[][] grid;
    /**
     * The button for starting the simulation.
     */
    private JButton startButton = new JButton("Start");
    private JButton stopButton = new JButton("Stop");
    private JButton resetButton = new JButton("Reset");
    private JButton exitButton = new JButton("Exit");

    public GameOfLifeGUI() {
        golGraph = new GameOfLifeGraph(this);
        initGameOfLifeGUI();
    }

    private void initGameOfLifeGUI() {
        setSize(golGraph.getWidth() * 30, golGraph.getHeight() * 30);

        startButton.addActionListener(this);
        stopButton.addActionListener(this);
        resetButton.addActionListener(this);
        exitButton.addActionListener(this);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 4));
        buttons.add(startButton);
        buttons.add(stopButton);
        buttons.add(resetButton);
        buttons.add(exitButton);

        JPanel field = new JPanel();
        field.setBackground(new Color(227, 204, 89));
        field.setLayout(new GridLayout(golGraph.getHeight(),
                golGraph.getWidth()));
        grid = new JLabel[golGraph.getWidth()][golGraph.getHeight()];

        for (int y = 0; y < golGraph.getHeight(); y++) {
            for (int x = 0; x < golGraph.getWidth(); x++) {
                grid[x][y] = new JLabel(II_EMPTY);
                grid[x][y].setVisible(true);
                field.add(grid[x][y]);
            }
        }

        Container display = getContentPane();
        display.setBackground(new Color(27, 204, 89));
        display.setLayout(new BorderLayout());
        display.add(field, BorderLayout.CENTER);
        display.add(buttons, BorderLayout.SOUTH);

        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        exitButton.setEnabled(true);
        golGraph.setSeed();
        updateAll();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            exitButton.setEnabled(false);
            golGraph.start();
        }

        if (e.getSource() == stopButton) {
            stopButton.setEnabled(false);
            startButton.setEnabled(true);
            exitButton.setEnabled(true);
            golGraph.stop();
        }
        if (e.getSource() == resetButton) {
            golGraph.initWorld();
            golGraph.setSeed();
            updateAll();

        }

        if (e.getSource() == exitButton) {
            golGraph.stop();
            System.exit(0);
        }
    }

    public void updateAll() {
        int width = golGraph.getWidth();
        int height = golGraph.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ImageIcon icon;

                char whatIsHere = golGraph.world[x][y];

                if (whatIsHere == 'X') {
                    icon = II_THING;
                } else {
                    icon = II_EMPTY;
                }

                grid[x][height - y - 1].setIcon(icon);
            }
        }

    }

}
