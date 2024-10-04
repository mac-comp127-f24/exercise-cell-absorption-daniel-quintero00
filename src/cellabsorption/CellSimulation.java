package cellabsorption;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;

import java.awt.Color;
import java.util.Random;
import java.awt.List;
import java.util.ArrayList;

@SuppressWarnings("SameParameterValue")
public class CellSimulation {

    private CanvasWindow canvas;
    private Random rand = new Random();
    private ArrayList<Cell> cells;

    public static void main(String[] args) {
        new CellSimulation();
    }

    public CellSimulation() {
        canvas = new CanvasWindow("Cell Absorption", 800, 800);
        populateCells();

        //noinspection InfiniteLoopStatement
        while (true) {
            for (Cell cell : cells) {
                Point canvasCenter = new Point(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0);
                cell.moveAround(canvasCenter);
                cell.grow(0.02);
            }
                canvas.draw();
                canvas.pause(10);
        }
    }

    private void populateCells() {
        double size = rand.nextInt(5) + 2;
        cells = new ArrayList<>();
        for (int i = 1; i <= 200; i++) {
            Cell cell = new Cell(rand.nextDouble() * (canvas.getWidth() - size),
            rand.nextDouble() * (canvas.getWidth() - size),
            size,
            Color.getHSBColor(rand.nextFloat(), rand.nextFloat() * 0.5f + 0.1f, 1));
            canvas.add(cell.getShape());
            cells.add(cell);
        }
    }

    private static double sqr(double x) {
        return x * x;
    }

}
