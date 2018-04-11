package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.Lab4;

public class Controller {
    @FXML private Canvas canvas;
    @FXML private Pane pane;
    private double start = -Math.PI;
    private double finish = 3.2*Math.PI;
    Lab4 lab4;

    @FXML
    public void initialize() {
        lab4 = new Lab4();
        canvas.widthProperty().bind(pane.widthProperty());
        canvas.heightProperty().bind(pane.heightProperty());

        canvas.widthProperty().addListener(e->draw());
        canvas.heightProperty().addListener(e->draw());
    }

    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITESMOKE);
        gc.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
//        gc.strokeLine(0,0,canvas.getWidth(), canvas.getHeight());
//        gc.strokeLine(canvas.getWidth(), 0, 0, canvas.getHeight());
        drawFunction(canvas.getWidth(), canvas.getHeight());
    }

    private void drawFunction(double width, double height) {
        double step = (finish-start)/width;
        double[] x = new double[(int)width+1];
        double[] y = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            x[i] = start + i*step;
            y[i] = lab4.f(x[i]);
        }
        double ymax = lab4.max(y);
        double ymin = lab4.min(y);

        double[] xe = new double[x.length];
        double[] ye = new double[y.length];
        for (int i = 0; i < x.length; i++) {
             xe[i] = width*(x[i]-start)/(finish-start);
             ye[i] = height*(y[i]-ymax)/(ymin-ymax);
        }
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (int i = 0; i < ye.length-1; i++) {
            gc.strokeLine(xe[i], ye[i], xe[i+1], ye[i+1]);
        }

        double xc = width*(0-start)/(finish-start);
        double yc = height*(0-ymax)/(ymin-ymax);
        gc.strokeLine(xc, 0, xc, height);
        gc.strokeLine(0, yc, width, yc);
    }
}
