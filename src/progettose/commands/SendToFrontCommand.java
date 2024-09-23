package progettose.commands;

import java.util.ArrayList;
import java.util.Map;
import javafx.scene.Group;
import javafx.scene.shape.Shape;
import progettose.application.CanvasClass;
import progettose.shapes.wrappers.ShapeInterface;
import progettose.shapes.wrappers.ShapeZComparator;

/**
 * Concrete command for moving all selected shapes on the canvas to the first
 * layer maintaining the hierarchy of the selected shapes.
 *
 */
public class SendToFrontCommand extends Command {

    /**
     * Class constructor
     *
     * @param app, command invoker
     * @param receiver, command receiver
     */
    public SendToFrontCommand(CanvasClass app, Receiver receiver) {
        super(app, receiver);
    }

    /**
     * Method for the execution of the command. It saves the previous context.
     *
     * @return true.
     */
    @Override
    public boolean execute() {
        int j = 0;
        ArrayList<ShapeInterface> fix = new ArrayList<>();
        fix.addAll(this.app.getShapesOnCanvas().values());
        fix.sort(new ShapeZComparator());
        for (ShapeInterface s : fix) {
            s.setZ(j);
            j++;
        }
        saveBackup();
        ArrayList<Group> selectedshapes = this.receiver.getSelectedShape();
        Map<Shape, ShapeInterface> shapesoncanvas = this.receiver.getPrevContext();
        ArrayList<ShapeInterface> temp = new ArrayList();
        for (Group a : selectedshapes) {
            temp.add(shapesoncanvas.get((Shape) a.getChildren().get(0)));
        }
        temp.sort(new ShapeZComparator());
        int amount = this.receiver.getSelectedShape().size();
        for (ShapeInterface a : temp) {
            shapesoncanvas.remove(a.getShape());
        }
        ArrayList<ShapeInterface> tempos = new ArrayList();
        for (ShapeInterface a : shapesoncanvas.values()) {
            tempos.add(a);
        }

        tempos.sort(new ShapeZComparator());
        int i = 0;
        for (ShapeInterface s : tempos) {
            if (i - amount + 1 >= 0) {
                tempos.get(i).setZ(i);
            }
            i++;
        }
        for (i = 0; i < amount; i++) {
            temp.get(i).setZ(tempos.size() + i);
        }
        for (ShapeInterface a : temp) {
            tempos.add(a);
        }
        tempos.sort(new ShapeZComparator());
        for (ShapeInterface s : tempos) {
            this.receiver.deleteShape(s);
        }
        for (ShapeInterface s : tempos) {
            this.receiver.addShape(s);
        }
        return true;
    }
}
