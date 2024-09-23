package progettose.tool;

import javafx.scene.layout.Pane;
import progettose.application.CanvasClass;
/**
 * Class to manage the creation of a tool for the creation of the line
 * 
 */
public class LineTool extends Tool {

    private double x;
    private double y;
    private Pane pane;

    public LineTool(Pane pane) {
        this.pane = pane;
    }
/**
     * This method set the ending X and Y coordinates checking orientation based on the previously saved starting X and Y
     * @param canvas the class that map the canvas on the interface
     * @param x ending x point
     * @param y ending y point
     */
    @Override
    public void mouseUp(CanvasClass canvas, double x, double y) {
        canvas.createShape("line", this.x, this.y, x, y, super.getInteriorColor(), super.getBorderColor());
    }
/**
     * This method set the ending starting X and Y coordinates
     * @param canvas the class that map the canvas on the interface
     * @param x starting x point
     * @param y starting y point
     */
    @Override
    public void mouseDown(CanvasClass canvas, double x, double y) {
        this.x = x;
        this.y = y;
    }
 /**
     * This method change the state of this tool changing his color 
     * @param selected True if the current tool is selected, false if is not
     */
    @Override
    public void setSelected(boolean selected) {
        if (selected) {
            pane.setStyle("-fx-background-color:lightgrey;");
        } else {
            pane.setStyle("-fx-background-color:white;");
        }
    }
}
