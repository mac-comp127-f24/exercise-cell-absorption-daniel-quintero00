package cellabsorption;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;

import java.awt.Color;
import java.util.Random;

@SuppressWarnings("SameParameterValue")
public class CellSimulation {

    private CanvasWindow canvas;
    private Random rand = new Random();
    private Cell cell;
    private static final double
        WIGGLINESS = 0.2,
        WANDER_FROM_CENTER = 60000;

    public static void main(String[] args) {
        new CellSimulation();
    }

    public CellSimulation() {
        canvas = new CanvasWindow("Cell Absorption", 800, 800);
        populateCells();

        //noinspection InfiniteLoopStatement
        while (true) {
            Point canvasCenter = new Point(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0);
            moveAround(canvasCenter);
            cell.grow(0.02);

            canvas.draw();
            canvas.pause(10);
        }
    }

    private void moveAround(Point centerOfGravity) {
        cell.getShape().moveBy(Math.cos(cell.getDirection()), Math.sin(cell.getDirection()));

        double distToCenter = cell.getShape().getCenter().distance(centerOfGravity);
        double angleToCenter = centerOfGravity.subtract(cell.getShape().getCenter()).angle();
        double turnTowardCenter = Cell.normalizeRadians(angleToCenter - cell.getDirection());

        cell.getDirection() = Cell.normalizeRadians(
            cell.getDirection()
                + (Math.random() - 0.5) * WIGGLINESS
                + turnTowardCenter * Math.tanh(distToCenter / WANDER_FROM_CENTER));
    }

    private void populateCells() {
        double size = rand.nextInt(5) + 2;
        cell = new Cell(
            rand.nextDouble() * (canvas.getWidth() - size),
            rand.nextDouble() * (canvas.getWidth() - size),
            size,
            Color.getHSBColor(rand.nextFloat(), rand.nextFloat() * 0.5f + 0.1f, 1));
        canvas.add(cell.getShape());
    }

    private static double sqr(double x) {
        return x * x;
    }

}
